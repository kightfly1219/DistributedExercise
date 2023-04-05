package com.example.orderserver.business.order.vo;

import com.example.orderserver.framework.serviceflow.output.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
public class OrderCreateRes extends BaseResponse {

}
