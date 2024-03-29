package com.itlaoqi.seata.points;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//6.3 排除默认数据源配置类
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@MapperScan(basePackages="com.itlaoqi.seata.points.dao")
public class PointsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PointsApplication.class, args);
    }

}
