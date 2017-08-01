package com.cyosp.mpa.api.rest.core.v1.controller;

import com.cyosp.mpa.api.rest.common.controller.CommonController;
import com.cyosp.mpa.api.rest.common.exception.DuplicatedNameException;
import com.cyosp.mpa.api.rest.common.exception.LineNotDeletedException;
import com.cyosp.mpa.api.rest.common.exception.LineNotInsertedException;
import com.cyosp.mpa.api.rest.common.exception.LineNotUpdatedException;
import com.cyosp.mpa.api.rest.core.v1.request.AccountRequest;
import com.cyosp.mpa.api.rest.core.v1.response.AccountResponse;
import com.cyosp.mpa.api.rest.core.v1.service.MpaService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by CYOSP on 2017-06-27.
 */
@RestController
@RequestMapping(CommonController.COMMON_API_PATH + MpaRestController.SUB_PATH)
@Getter
public class MpaRestController {

    public static final String SUB_PATH = "/core/v1";

    @Autowired
    private MpaService mpaService;

    @PostMapping("/accounts/add")
    public AccountResponse addAccount(@Validated @RequestBody AccountRequest accountRequest) {
        AccountResponse ret = new AccountResponse();

        try {
            ret = getMpaService().addAccount(accountRequest);
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

    @PostMapping("/accounts/upd/{id}")
    public AccountResponse updateAccount(@PathVariable
                                                 long id, @Validated @RequestBody AccountRequest accountRequest) {
        AccountResponse ret = new AccountResponse();

        try {
            ret = getMpaService().updateAccount(id, accountRequest);
        } catch (LineNotUpdatedException e) {
            ret.setId(AccountResponse.ID_LINE_NOT_UPDATED);
        } catch (DuplicatedNameException e) {
            ret.setId(AccountResponse.ID_DUPLICATED_NAME);
        }

        return ret;
    }

    @PostMapping("/accounts/del/{id}")
    public AccountResponse deleteAccount(@PathVariable
                                                 long id) {
        AccountResponse ret = new AccountResponse();

        try {
            ret = getMpaService().deleteAccount(id);
        } catch (LineNotDeletedException e) {
            ret.setId(AccountResponse.ID_LINE_NOT_DELETED);
        }

        return ret;
    }
}
