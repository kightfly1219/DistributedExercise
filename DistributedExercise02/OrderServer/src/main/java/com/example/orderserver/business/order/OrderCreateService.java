package com.example.orderserver.business.order;

import com.example.orderserver.business.order.vo.OrderCreateReq;
import com.example.orderserver.business.order.vo.OrderCreateRes;
import com.example.orderserver.entity.AccountEntity;
import com.example.orderserver.entity.OrderEntity;
import com.example.orderserver.framework.serviceflow.blogic.ServiceLogic;
import com.example.orderserver.repository.AccountRepository;
import com.example.orderserver.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class OrderCreateService implements ServiceLogic<OrderCreateReq, OrderCreateRes> {

    final AccountRepository accountRepository;

    final OrderRepository orderRepository;

    final StringRedisTemplate accoutRedis;

    public OrderCreateService(AccountRepository accountRepository, OrderRepository orderRepository
            , StringRedisTemplate accoutRedis) {
        this.accountRepository = accountRepository;
        this.orderRepository = orderRepository;
        this.accoutRedis = accoutRedis;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderCreateRes execute(OrderCreateReq input) throws Exception {
        // 用户信息验证
        String accountId = input.getAccountId();
        Boolean hasAccount = checkAcountId(accountId);
        if (!hasAccount) {
            throw new Exception("用户信息验证 失败");
        }

        // 订单创建
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(input.getOrderId());
        orderEntity.setAccountId(input.getAccountId());
        orderEntity.setOrderDetail(input.getOrderDetail());
        orderRepository.save(orderEntity);
        return null;
    }

    private Boolean checkAcountId(String accountId) {
        Boolean haskey = accoutRedis.hasKey(accountId);
        if (haskey) {
            return true;
        }
        List<AccountEntity> accountEntityList = accountRepository.findByAccountId(accountId);
        if (accountEntityList.size() >= 1) {
            AccountEntity entity = accountEntityList.get(0);
            accoutRedis.opsForValue().set(entity.getAccountId(), entity.getName());
            return true;
        }
        return false;
    }
}
