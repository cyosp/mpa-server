package com.cyosp.mpa.api.rest.homebank.v1dot2.service;

import com.cyosp.mpa.api.rest.common.exception.DataNotSavedException;
import com.cyosp.mpa.api.rest.common.exception.DuplicatedNameException;
import com.cyosp.mpa.api.rest.common.exception.LineNotDeletedException;
import com.cyosp.mpa.api.rest.common.exception.LineNotUpdatedException;
import com.cyosp.mpa.api.rest.homebank.v1dot2.mapper.XmlMapper;
import com.cyosp.mpa.api.rest.homebank.v1dot2.model.*;
import com.cyosp.mpa.api.rest.homebank.v1dot2.request.AccountRequest;
import com.cyosp.mpa.api.rest.homebank.v1dot2.response.*;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CYOSP on 2017-06-27.
 */
@Service
@Getter
public class HomebankServiceImpl implements HomebankService {

    @Autowired
    private XmlMapper xmlMapper;

    @Override
    public void reload() {
        getXmlMapper().loadXmlFile();
    }

    @Override
    public InfosResponse getInfos() {

        InfosResponse ret = new InfosResponse();

        HomeBank homeBank = getXmlMapper().getInfos();
        ret.setV(homeBank.getV());
        ret.setD(homeBank.getD());

        return ret;
    }

    @Override
    public PropertiesResponse getProperties() {
        PropertiesResponse ret = new PropertiesResponse();

        Properties properties = getXmlMapper().getProperties();
        BeanUtils.copyProperties(properties, ret);

        return ret;
    }

    //
    // Accounts
    //

    @Override
    public AccountResponse addAccount(AccountRequest accountRequest) throws DataNotSavedException, DuplicatedNameException {
        Account account = new Account();
        account.setName(accountRequest.getName());

        getXmlMapper().addAccount(account);

        AccountResponse accountResponse = new AccountResponse();
        BeanUtils.copyProperties(account, accountResponse);

        return accountResponse;
    }

    @Override
    public List<AccountResponse> getAccounts() {

        List<AccountResponse> ret = new ArrayList<>();

        for (Account account : getXmlMapper().getAccounts()) {
            AccountResponse accountResponse = new AccountResponse();
            BeanUtils.copyProperties(account, accountResponse);
            ret.add(accountResponse);
        }

        return ret;
    }

    @Override
    public AccountResponse getAccountById(long id) {
        return null;
    }

    @Override
    public AccountResponse updateAccount(long id, AccountRequest accountRequest) throws LineNotUpdatedException, DuplicatedNameException {
        return null;
    }

    @Override
    public AccountResponse deleteAccount(long id) throws LineNotDeletedException {
        return null;
    }

    //
    // Categories
    //

    @Override
    public List<CategoryResponse> getCategories() {
        List<CategoryResponse> ret = new ArrayList<>();

        for (Category category : getXmlMapper().getCategories()) {
            CategoryResponse categoryResponse = new CategoryResponse();
            BeanUtils.copyProperties(category, categoryResponse);
            ret.add(categoryResponse);
        }

        return ret;
    }

    //
    // Currencies
    //

    @Override
    public List<CurrencyResponse> getCurrencies() {
        List<CurrencyResponse> ret = new ArrayList<>();

        for (Currency currency : getXmlMapper().getCurrencies()) {
            CurrencyResponse currencyResponse = new CurrencyResponse();
            BeanUtils.copyProperties(currency, currencyResponse);
            ret.add(currencyResponse);
        }

        return ret;
    }

    //
    // Favorites
    //

    @Override
    public List<FavoriteResponse> getFavorites() {
        List<FavoriteResponse> ret = new ArrayList<>();

        for (Favorite favorite : getXmlMapper().getFavorites()) {
            FavoriteResponse favoriteResponse = new FavoriteResponse();
            BeanUtils.copyProperties(favorite, favoriteResponse);
            ret.add(favoriteResponse);
        }

        return ret;
    }

    //
    // Operations
    //

    @Override
    public List<OperationResponse> getOperations() {
        List<OperationResponse> ret = new ArrayList<>();

        for (Operation operation : getXmlMapper().getOperations()) {
            OperationResponse operationResponse = new OperationResponse();
            BeanUtils.copyProperties(operation, operationResponse);
            ret.add(operationResponse);
        }

        return ret;
    }

    //
    // Payees
    //

    @Override
    public List<PayeeResponse> getPayees() {
        List<PayeeResponse> ret = new ArrayList<>();

        for (Payee payee : getXmlMapper().getPayees()) {
            PayeeResponse payeeResponse = new PayeeResponse();
            BeanUtils.copyProperties(payee, payeeResponse);
            ret.add(payeeResponse);
        }

        return ret;
    }

    //
    // Tags
    //

    @Override
    public List<TagResponse> getTags() {
        List<TagResponse> ret = new ArrayList<>();

        for (Tag tag : getXmlMapper().getTags()) {
            TagResponse tagResponse = new TagResponse();
            BeanUtils.copyProperties(tag, tagResponse);
            ret.add(tagResponse);
        }

        return ret;
    }
}
