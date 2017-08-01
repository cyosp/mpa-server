package com.cyosp.mpa.api.rest.homebank.v1dot2.controller;

import com.cyosp.mpa.api.rest.common.controller.CommonController;
import com.cyosp.mpa.api.rest.common.exception.DataNotSavedException;
import com.cyosp.mpa.api.rest.common.exception.DuplicatedNameException;
import com.cyosp.mpa.api.rest.homebank.v1dot2.request.AccountRequest;
import com.cyosp.mpa.api.rest.homebank.v1dot2.response.*;
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
@RequestMapping(CommonController.COMMON_API_PATH + HomebankRestController.SUB_PATH)
@Getter
public class HomebankRestController {

    public static final String SUB_PATH = "/homebank/v1.2";

    @Autowired
    private HomebankService homebankService;

    @GetMapping("/reload")
    public void reload() {
        getHomebankService().reload();
    }

    @GetMapping("/infos")
    public InfosResponse getInfos() {
        return getHomebankService().getInfos();
    }

    @GetMapping("/properties")
    public PropertiesResponse getProperties() {
        return getHomebankService().getProperties();
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

    //
    // Favorites
    //

    @GetMapping("/favorites")
    public List<FavoriteResponse> getFavorites() {
        return getHomebankService().getFavorites();
    }

    //
    // Operations
    //

    @GetMapping("/operations")
    public List<OperationResponse> getOperations() {
        return getHomebankService().getOperations();
    }

    //
    // Payees
    //

    @GetMapping("/payees")
    public List<PayeeResponse> getPayees() {
        return getHomebankService().getPayees();
    }


    //
    // Tags
    //

    @GetMapping("/tags")
    public List<TagResponse> getTags() {
        return getHomebankService().getTags();
    }
}
