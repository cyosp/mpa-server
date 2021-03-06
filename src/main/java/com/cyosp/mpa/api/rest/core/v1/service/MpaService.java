package com.cyosp.mpa.api.rest.core.v1.service;

import com.cyosp.mpa.api.rest.common.exception.DuplicatedNameException;
import com.cyosp.mpa.api.rest.common.exception.LineNotDeletedException;
import com.cyosp.mpa.api.rest.common.exception.LineNotInsertedException;
import com.cyosp.mpa.api.rest.common.exception.LineNotUpdatedException;
import com.cyosp.mpa.api.rest.core.v1.mapper.AccountMapper;
import com.cyosp.mpa.api.rest.core.v1.model.Account;
import com.cyosp.mpa.api.rest.core.v1.request.AccountRequest;
import com.cyosp.mpa.api.rest.core.v1.response.AccountResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CYOSP on 2017-06-27.
 */
@Service
public class MpaService {

    final AccountMapper accountMapper;

    @Autowired
    public MpaService(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    //Transactional
    public Account addAcount(Account account) throws LineNotInsertedException, DuplicatedNameException {
        try {
            if (accountMapper.addAccount(account) != 1)
                throw new LineNotInsertedException();
            account = accountMapper.getAccountById(account.getId());
        } catch (DuplicateKeyException e) {
            throw new DuplicatedNameException();
        }

        return account;
    }

    public AccountResponse addAccount(AccountRequest accountRequest) throws DuplicatedNameException, LineNotInsertedException {
        Account account = new Account();
        account.setName(accountRequest.getName());
        account.setBalance(new BigDecimal(0));

        account = addAcount(account);

        AccountResponse accountResponse = new AccountResponse();
        BeanUtils.copyProperties(account, accountResponse);

        return accountResponse;
    }

    public List<AccountResponse> getAccounts() {

        List<AccountResponse> ret = new ArrayList<>();

        for (Account account : accountMapper.getAccounts()) {
            AccountResponse accountResponse = new AccountResponse();
            BeanUtils.copyProperties(account, accountResponse);
            ret.add(accountResponse);
        }

        return ret;
    }

    public AccountResponse getAccountById(long id) {
        AccountResponse ret = new AccountResponse();

        Account account = accountMapper.getAccountById(id);
        if (account != null)
            BeanUtils.copyProperties(account, ret);
        else
            ret.setId(AccountResponse.ID_NOT_FOUND);

        return ret;
    }

    //@Transactional
    public Account updateAcount(Account account) throws LineNotUpdatedException, DuplicatedNameException {
        try {
            if (accountMapper.updateAccount(account) != 1)
                throw new LineNotUpdatedException();
            account = accountMapper.getAccountById(account.getId());
        } catch (DuplicateKeyException e) {
            throw new DuplicatedNameException();
        }

        return account;
    }

    public AccountResponse updateAccount(long id, AccountRequest accountRequest) throws LineNotUpdatedException, DuplicatedNameException {
        Account account = new Account();
        account.setId(id);
        account.setName(accountRequest.getName());

        account = updateAcount(account);

        AccountResponse accountResponse = new AccountResponse();
        BeanUtils.copyProperties(account, accountResponse);

        return accountResponse;
    }

    public AccountResponse deleteAccount(long id) throws LineNotDeletedException {

        if (accountMapper.deleteAccount(id) != 1)
            throw new LineNotDeletedException();

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(id);

        return accountResponse;
    }
}
