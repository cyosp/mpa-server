package com.cyosp.mpa.controller;

import com.cyosp.mpa.exception.DuplicatedNameException;
import com.cyosp.mpa.exception.LineNotInsertedException;
import com.cyosp.mpa.request.AddAccountRequest;
import com.cyosp.mpa.response.AccountResponse;
import com.cyosp.mpa.service.MpaService;
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
public class MpaRestControllerV1 {

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
