package com.itlaoqi.seata.bussiness.service;

import com.itlaoqi.seata.bussiness.client.OrderServiceClient;
import com.itlaoqi.seata.bussiness.client.AccountServiceClient;
import com.itlaoqi.seata.bussiness.client.entity.Order;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 业务逻辑
 */
@Service
public class BussinessService {
    @Resource
    private AccountServiceClient accountServiceClient;

    @Resource
    private OrderServiceClient orderServiceClient;


    /**
     * 商品销售
     * @param goodsCode 商品编码
     * @param quantity 销售数量
     * @param username 用户名
     * @param points 增加积分
     * @param amount 订单金额
     * @return 订单对象
     */
    @GlobalTransactional(name = "fsp-sale" , timeoutMills = 20000 , rollbackFor = Exception.class)
    //@Transactional
    public Order sale(String goodsCode , Integer quantity ,String username ,Integer points, Float amount ){
        accountServiceClient.increase(username, points);
        Order order = orderServiceClient.create(goodsCode, quantity, username, points, amount);
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return order;
    }
}
