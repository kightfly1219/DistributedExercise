package com.example.orderserver.business.order;

import com.example.orderserver.business.order.vo.OrderCreateReq;
import com.example.orderserver.business.order.vo.OrderCreateRes;
import com.example.orderserver.framework.serviceflow.output.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Validated
public class OrderCreateController {

    final OrderCreateService accountCreateService;


    public OrderCreateController(OrderCreateService accountCreateService) {
        this.accountCreateService = accountCreateService;
    }

    @PostMapping("/order/create")
    public R<OrderCreateRes> search() {
        OrderCreateReq input = new OrderCreateReq();
        OrderCreateRes output = accountCreateService.execute(input);
        return R.ok(output);
    }
}
