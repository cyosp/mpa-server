package com.cyosp.mpa.service;

import com.cyosp.mpa.exception.DuplicatedNameException;
import com.cyosp.mpa.mapper.AccountMapper;
import com.cyosp.mpa.model.Account;
import com.cyosp.mpa.request.AddAccountRequest;
import com.cyosp.mpa.response.AddAccountResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by CYOSP on 2017-06-27.
 */
@Service
@Getter
public class MpaServiceV1 implements MpaService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public AddAccountResponse addAccount(AddAccountRequest addAccountRequest) throws DuplicatedNameException {
        Account account = new Account();
        account.setName(addAccountRequest.getName());
        account.setBalance(new BigDecimal(0));

        try {
            getAccountMapper().addAccount(account);
        } catch (DuplicateKeyException e) {
            throw new DuplicatedNameException();
        }

        AddAccountResponse addAccountResponse = new AddAccountResponse();
        addAccountResponse.setId(account.getId());
        addAccountResponse.setBalance(account.getBalance());

        return addAccountResponse;
    }

    @Override
    public List<Account> getAccounts() {
        return getAccountMapper().getAccounts();
    }
}
