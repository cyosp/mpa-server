package com.cyosp.mpa.service;

import com.cyosp.mpa.exception.DuplicatedNameException;
import com.cyosp.mpa.exception.LineNotInsertedException;
import com.cyosp.mpa.mapper.AccountMapper;
import com.cyosp.mpa.model.Account;
import com.cyosp.mpa.request.AddAccountRequest;
import com.cyosp.mpa.response.AccountResponse;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CYOSP on 2017-06-27.
 */
@Service
@Getter
public class MpaServiceV1 implements MpaService {

    @Autowired
    private AccountMapper accountMapper;

    @Transactional
    private Account addAcount(Account account) throws LineNotInsertedException, DuplicatedNameException {
        try {
            if (getAccountMapper().addAccount(account) != 1)
                throw new LineNotInsertedException();
            account = getAccountMapper().getAccountById(account.getId());
        } catch (DuplicateKeyException e) {
            throw new DuplicatedNameException();
        }

        return account;
    }

    @Override
    public AccountResponse addAccount(AddAccountRequest addAccountRequest) throws DuplicatedNameException, LineNotInsertedException {
        Account account = new Account();
        account.setName(addAccountRequest.getName());
        account.setBalance(new BigDecimal(0));

        account = addAcount(account);

        AccountResponse accountResponse = new AccountResponse();
        BeanUtils.copyProperties(account, accountResponse);

        return accountResponse;
    }

    @Override
    public List<AccountResponse> getAccounts() {

        List<AccountResponse> ret = new ArrayList<>();

        for (Account account : getAccountMapper().getAccounts()) {
            AccountResponse accountResponse = new AccountResponse();
            BeanUtils.copyProperties(account, accountResponse);
            ret.add(accountResponse);
        }

        return ret;
    }

    @Override
    public AccountResponse getAccountById(long id) {
        AccountResponse ret = new AccountResponse();

        Account account = getAccountMapper().getAccountById(id);
        if (account != null)
            BeanUtils.copyProperties(account, ret);
        else
            ret.setId(AccountResponse.ID_NOT_FOUND);

        return ret;
    }
}
