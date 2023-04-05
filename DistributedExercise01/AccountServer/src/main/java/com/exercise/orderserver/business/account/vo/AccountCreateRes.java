package com.exercise.orderserver.business.account.vo;

import com.exercise.orderserver.framework.serviceflow.output.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
public class AccountCreateRes extends BaseResponse {

}
