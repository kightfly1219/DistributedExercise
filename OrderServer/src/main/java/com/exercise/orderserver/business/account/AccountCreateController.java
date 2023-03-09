package com.exercise.orderserver.business.account;

import com.exercise.orderserver.business.account.vo.AccountCreateReq;
import com.exercise.orderserver.business.account.vo.AccountCreateRes;
import com.exercise.orderserver.framework.serviceflow.output.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Validated
public class AccountCreateController {

    final AccountCreateService accountCreateService;


    public AccountCreateController(AccountCreateService accountCreateService) {
        this.accountCreateService = accountCreateService;
    }

    @PostMapping("/accounts/create")
    public R<AccountCreateRes> search() {
        AccountCreateReq input = new AccountCreateReq();
        AccountCreateRes output = accountCreateService.execute(input);
        return R.ok(output);
    }
}
