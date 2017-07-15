package com.cyosp.mpa.controller;

import com.cyosp.mpa.model.Account;
import com.cyosp.mpa.request.AddAccountRequest;
import com.cyosp.mpa.response.AddAccountResponse;
import com.cyosp.mpa.service.MpaService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by CYOSP on 2017-06-27.
 */
@RestController
@RequestMapping("/mpa/api/rest/v1")
public class MpaRestControllerV1 {

    @Autowired
    @Getter
    private MpaService mpaService;

    @PostMapping("/accounts/add")
    public AddAccountResponse addAccount(@RequestBody AddAccountRequest addAccountRequest) {
        return getMpaService().addAccount(addAccountRequest);
    }

    @GetMapping("/accounts")
    public String getAccounts() {
        StringBuffer ret = new StringBuffer();

        for (Account account : getMpaService().getAccounts())
            ret.append(account.toString() + "</br>");

        return ret.toString();
    }
}
