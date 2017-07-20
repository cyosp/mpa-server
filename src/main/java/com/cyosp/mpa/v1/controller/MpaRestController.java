package com.cyosp.mpa.v1.controller;

import com.cyosp.mpa.v1.exception.DuplicatedNameException;
import com.cyosp.mpa.v1.exception.LineNotInsertedException;
import com.cyosp.mpa.v1.request.AddAccountRequest;
import com.cyosp.mpa.v1.response.AccountResponse;
import com.cyosp.mpa.v1.service.MpaService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by CYOSP on 2017-06-27.
 */
@RestController
@RequestMapping("/mpa/api/rest/v1")
public class MpaRestController {

    @Autowired
    @Getter
    private MpaService mpaService;

    @PostMapping("/accounts/add")
    public AccountResponse addAccount(@Validated @RequestBody AddAccountRequest addAccountRequest) {
        AccountResponse ret = new AccountResponse();

        try {
            ret = getMpaService().addAccount(addAccountRequest);
        } catch (LineNotInsertedException e) {
            ret.setId(AccountResponse.ID_LINE_NOT_INSERTED);
        } catch (DuplicatedNameException e) {
            ret.setId(AccountResponse.ID_DUPLICATED_NAME);
        }

        return ret;
    }

    @GetMapping("/accounts")
    public List<AccountResponse> getAccounts() {
        return getMpaService().getAccounts();
    }

    @GetMapping("/accounts/{id}")
    public AccountResponse getAccount(@PathVariable
                                              long id) {
        return getMpaService().getAccountById(id);
    }
}
