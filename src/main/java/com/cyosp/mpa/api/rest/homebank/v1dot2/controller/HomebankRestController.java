package com.cyosp.mpa.api.rest.homebank.v1dot2.controller;

import com.cyosp.mpa.api.rest.common.controller.CommonController;
import com.cyosp.mpa.api.rest.common.exception.DataNotSavedException;
import com.cyosp.mpa.api.rest.common.exception.DuplicatedNameException;
import com.cyosp.mpa.api.rest.homebank.v1dot2.model.PaymentMode;
import com.cyosp.mpa.api.rest.homebank.v1dot2.request.AccountRequest;
import com.cyosp.mpa.api.rest.homebank.v1dot2.request.OperationRequest;
import com.cyosp.mpa.api.rest.homebank.v1dot2.response.*;
import com.cyosp.mpa.api.rest.homebank.v1dot2.service.HomebankService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Created by CYOSP on 2017-07-24.
 */
@RestController
@RequestMapping(CommonController.COMMON_API_PATH + HomebankRestController.SUB_PATH)
public class HomebankRestController {

    public static final String SUB_PATH = "/homebank/v1.2";

    final HomebankService homebankService;

    @Autowired
    public HomebankRestController(HomebankService homebankService) {
        this.homebankService = homebankService;
    }

    @GetMapping("/reload")
    public void reload() {
        homebankService.reload();
    }

    @GetMapping("/payment-modes")
    public List<PaymentModeResponse> getPaymentModes() {
        return PaymentMode.getPaymentModes().stream().map(pm -> new PaymentModeResponse(pm.getCode(), pm.getName(), pm.getManaged())).collect(Collectors.toList());
    }

    @GetMapping("/infos")
    public InfosResponse getInfos() {
        return homebankService.getInfos();
    }

    @GetMapping("/properties")
    public PropertiesResponse getProperties() {
        return homebankService.getProperties();
    }

    //
    // Accounts
    //

    @PostMapping("/accounts/add")
    public AccountResponse addAccount(@Validated @RequestBody AccountRequest accountRequest) {
        AccountResponse ret = new AccountResponse();

        try {
            ret = homebankService.addAccount(accountRequest);
        } catch (DataNotSavedException e) {
            ret.setKey(RootResponse.KEY_DATA_NOT_SAVED);
        } catch (DuplicatedNameException e) {
            ret.setKey(RootResponse.KEY_DUPLICATED_NAME);
        }

        return ret;
    }

    @GetMapping("/accounts")
    public List<AccountResponse> getAccounts() {
        return homebankService.getAccounts();
    }

    @GetMapping("/accounts/{id}/operations")
    public List<OperationResponse> getOperationsByAccount(@PathVariable int id) {
        return homebankService.getOperationsByAccount(id);
    }

    @GetMapping("/accounts/{id}/categories")
    public List<CategoryResponse> getCategoriesByAccount(@PathVariable int id) {
        return homebankService.getCategoriesByAccount(id);
    }

    @GetMapping("/accounts/{id}/payees")
    public List<PayeeResponse> getPayeesByAccount(@PathVariable int id) {
        return homebankService.getPayeesByAccount(id);
    }

    @PostMapping("/accounts/{id}/operations/add")
    public OperationResponse addOperationsByAccount(@PathVariable int id, @RequestBody OperationRequest operationRequest) {

        OperationResponse ret = new OperationResponse();

        try {

            StringJoiner stringJoiner = new StringJoiner(" ", "Info received:", "");
            stringJoiner.add(operationRequest.getDate().toString());
            stringJoiner.add(operationRequest.getPayee());
            stringJoiner.add(operationRequest.getAmount().toString());
            stringJoiner.add(operationRequest.getCategory());
            stringJoiner.add(operationRequest.getWording());
            System.out.println(stringJoiner);

            ret = homebankService.addOperationByAccount(id, operationRequest);


        } catch (DataNotSavedException e) {
            ret.setKey(RootResponse.KEY_DATA_NOT_SAVED);
        }

        return ret;
    }

    //
    // Currencies
    //

    @GetMapping("/currencies")
    public List<CurrencyResponse> getCurrencies() {
        return homebankService.getCurrencies();
    }

    //
    // Favorites
    //

    @GetMapping("/favorites")
    public List<FavoriteResponse> getFavorites() {
        return homebankService.getFavorites();
    }

    //
    // Operations
    //

    @GetMapping("/operations")
    public List<OperationResponse> getOperations() {
        return homebankService.getOperations();
    }

    //
    // Tags
    //

    @GetMapping("/tags")
    public List<TagResponse> getTags() {
        return homebankService.getTags();
    }
}
