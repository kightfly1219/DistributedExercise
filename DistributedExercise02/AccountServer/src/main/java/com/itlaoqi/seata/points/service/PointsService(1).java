package com.itlaoqi.seata.points.service;


import com.itlaoqi.seata.points.dao.PointsDAO;
import com.itlaoqi.seata.points.entity.Points;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 会员积分服务
 */
@Service
public class PointsService {
    @Resource
    private PointsDAO pointsDAO;

    /**
     * 会员增加积分
     * @param username 用户名
     * @param quantity 增加的积分
     * @return 积分对象
     */
    public Points increase(String username, Integer quantity) {
        Points points = pointsDAO.findByUsername(username);
        points.setQuantity(points.getQuantity() + quantity);
        pointsDAO.update(points);
        return points;
    }
}
