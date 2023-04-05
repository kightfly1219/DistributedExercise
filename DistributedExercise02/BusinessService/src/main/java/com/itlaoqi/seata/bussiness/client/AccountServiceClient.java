package com.itlaoqi.seata.bussiness.client;

import com.itlaoqi.seata.bussiness.client.entity.Points;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("AccountServer")
public interface AccountServiceClient {
    @GetMapping("/increase")
    public Points increase(@RequestParam String username, @RequestParam Integer quantity);
}
