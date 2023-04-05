package com.exercise.orderserver.business.account.vo;

import com.exercise.orderserver.framework.serviceflow.input.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
public class AccountCreateReq extends BaseRequest {

    private Integer id;

    private String accountId;

    private String name;

    private String emailAddress;
}
