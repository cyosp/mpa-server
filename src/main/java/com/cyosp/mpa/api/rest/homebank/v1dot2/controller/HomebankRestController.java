package com.cyosp.mpa.api.rest.homebank.v1dot2.controller;

import com.cyosp.mpa.api.rest.common.exception.DataNotSavedException;
import com.cyosp.mpa.api.rest.common.exception.DuplicatedNameException;
import com.cyosp.mpa.api.rest.homebank.v1dot2.request.AccountRequest;
import com.cyosp.mpa.api.rest.homebank.v1dot2.response.AccountResponse;
import com.cyosp.mpa.api.rest.homebank.v1dot2.response.CategoryResponse;
import com.cyosp.mpa.api.rest.homebank.v1dot2.response.CurrencyResponse;
import com.cyosp.mpa.api.rest.homebank.v1dot2.service.HomebankService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by CYOSP on 2017-07-24.
 */
@RestController
@RequestMapping("/mpa/api/rest/homebank/v1.2")
@Getter
public class HomebankRestController {

    @Autowired
    private HomebankService homebankService;

    @GetMapping("/reload")
    public void reload() {
        getHomebankService().reload();
    }

    //
    // Accounts
    //

    @PostMapping("/accounts/add")
    public AccountResponse addAccount(@Validated @RequestBody AccountRequest accountRequest) {
        AccountResponse ret = new AccountResponse();

        try {
            ret = getHomebankService().addAccount(accountRequest);
        } catch (DataNotSavedException e) {
            ret.setKey(AccountResponse.KEY_DATA_NOT_SAVED);
        } catch (DuplicatedNameException e) {
            ret.setKey(AccountResponse.KEY_DUPLICATED_NAME);
        }

        return ret;
    }

    @GetMapping("/accounts")
    public List<AccountResponse> getAccounts() {
        return getHomebankService().getAccounts();
    }

    //
    // Categories
    //

    @GetMapping("/categories")
    public List<CategoryResponse> getCategories() {
        return getHomebankService().getCategories();
    }

    //
    // Currencies
    //
    @GetMapping("/currencies")
    public List<CurrencyResponse> getCurrencies() {
        return getHomebankService().getCurrencies();
    }


}
