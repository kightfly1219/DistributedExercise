package com.example.orderserver.recivemq;

import com.example.orderserver.entity.AccountEntity;
import com.example.orderserver.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ReciveRockeMQ {

    final DefaultMQPushConsumer consumer;
    final StringRedisTemplate accoutRedis;
    final AccountRepository accountRepository;

    public ReciveRockeMQ(StringRedisTemplate accoutRedis, AccountRepository accountRepository) {
        this.accoutRedis = accoutRedis;
        consumer = new DefaultMQPushConsumer("group_1");
        this.accountRepository = accountRepository;
    }

    public void start() throws MQClientException {
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("topic", "Account Update");
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                context.setAutoCommit(true);
                for (MessageExt msg : msgs) {
                    AccountEntity accountEntity =  (AccountEntity) byteToObject(msg.getBody());
                    updateAccountEntity(accountEntity);
                }

                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
    }

    private void updateAccountEntity(AccountEntity accountEntity) {
        // 缓存保存
        String key = accountEntity.getAccountId();
        accoutRedis.opsForValue().set(key, accountEntity.getName());

        // 数据库保存
        List<AccountEntity> accountEntityList = accountRepository.findByAccountId(accountEntity.getAccountId());
        AccountEntity entity = null;
        if (accountEntityList.size() == 0) {
            entity = new AccountEntity();
        } else {
            entity = accountEntityList.get(0);
        }

        entity.setAccountId(accountEntity.getAccountId());
        entity.setEmailAddress(accountEntity.getEmailAddress());
        entity.setName(accountEntity.getName());
        accountRepository.save(entity);
    }

    private Object byteToObject(byte[] bytes) {
        Object obj = new Object();
        // TODO
        return(obj);
    }
}
