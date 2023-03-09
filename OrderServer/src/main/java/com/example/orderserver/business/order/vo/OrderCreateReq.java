package com.example.orderserver.business.order.vo;

import com.example.orderserver.framework.serviceflow.input.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
public class OrderCreateReq extends BaseRequest {
    private String accountId;

    private String orderId;

    private String orderDetail;
}
