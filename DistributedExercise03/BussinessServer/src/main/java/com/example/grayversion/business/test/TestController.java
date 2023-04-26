package com.example.grayversion.test;

import cuit.epoch.pymjl.entity.User;
import cuit.epoch.pymjl.result.CommonResult;
import cuit.epoch.pymjl.result.ResultUtils;
import cuit.epoch.pymjl.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
public class TestController {
    private static final String SERVICE_URL = "http://order-service";

    @Resource
    RestTemplate restTemplate;

    @DubboReference
    OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult orderCreate() {
        return restTemplate.getForObject(SERVICE_URL + "/order/create", CommonResult.class);
    }

    @GetMapping("/register")
    public CommonResult<String> register() {
        orderService.register();
        return ResultUtils.success();
    }
}
