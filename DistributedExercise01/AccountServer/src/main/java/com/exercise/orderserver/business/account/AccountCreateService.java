package com.exercise.orderserver.business.account;

import com.exercise.orderserver.business.account.vo.AccountCreateReq;
import com.exercise.orderserver.business.account.vo.AccountCreateRes;
import com.exercise.orderserver.entity.AccountEntity;
import com.exercise.orderserver.framework.serviceflow.blogic.ServiceLogic;
import com.exercise.orderserver.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;

@Service
@Slf4j
public class AccountCreateService implements ServiceLogic<AccountCreateReq, AccountCreateRes> {

    final AccountRepository accountRepository;

    final StringRedisTemplate accoutRedis;

    final DefaultMQProducer producer;

    public AccountCreateService(AccountRepository accountRepository, StringRedisTemplate accoutRedis, DefaultMQProducer producer) {
        this.accountRepository = accountRepository;
        this.accoutRedis = accoutRedis;
        this.producer = producer;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccountCreateRes execute(AccountCreateReq input) {
        // 用户情报查询
        AccountEntity accountEntity = accountRepository.findById(input.getId()).get();

        if (accountEntity == null) {
            accountEntity = new AccountEntity();
        }

        // 用户情报登录
        accountEntity.setAccountId(input.getAccountId());
        accountEntity.setName(input.getName());
        accountEntity.setEmailAddress(input.getEmailAddress());

        accountRepository.save(accountEntity);

        // 用户缓存保存
        String key = accountEntity.getId().toString();
        try
        {
            accoutRedis.opsForValue().set(key, accountEntity.g, Duration.ofDays(60));
        } catch (Exception ex) {
            log.warn("Redis缓存保存失败" + ex);
        }

        // MQ 发送消息
        try {
            Message msg = new Message("topic","Account Update", key, objectToByte(accountEntity));

            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Long id = (Long) arg;  //根据订单id选择发送queue
                    long index = id % mqs.size();
                    return mqs.get((int) index);
                }
            }, key);
        }catch (Exception ex){
            log.warn("MQ 发送消息失败" + ex);
        }


        return new AccountCreateRes();
    }


    private byte[] objectToByte(Object obj) {
        byte[] bytes = {};
        // TODO
        return(bytes);
    }
}
