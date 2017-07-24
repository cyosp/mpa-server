package com.cyosp.mpa.api.rest.homebank.v1dot2.service;

import com.cyosp.mpa.api.rest.common.exception.*;
import com.cyosp.mpa.api.rest.homebank.v1dot2.mapper.XmlMapper;
import com.cyosp.mpa.api.rest.homebank.v1dot2.model.Account;
import com.cyosp.mpa.api.rest.homebank.v1dot2.request.AccountRequest;
import com.cyosp.mpa.api.rest.homebank.v1dot2.response.AccountResponse;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public void reload()
    {
        getXmlMapper().loadXmlFile();
    }

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
            accountResponse.setId(account.getKey());
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
}
