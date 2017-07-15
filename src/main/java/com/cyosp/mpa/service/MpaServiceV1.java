package com.cyosp.mpa.service;

import com.cyosp.mpa.mapper.AccountMapper;
import com.cyosp.mpa.model.Account;
import com.cyosp.mpa.request.AddAccountRequest;
import com.cyosp.mpa.response.AddAccountResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CYOSP on 2017-06-27.
 */
@Service
@Getter
public class MpaServiceV1 implements MpaService {

    @Autowired
    private AccountMapper accountMapper;

    public AddAccountResponse addAccount(AddAccountRequest addAccountRequest) {
        Account account = new Account();
        account.setName(addAccountRequest.getName());

        getAccountMapper().addAccount(account);

        AddAccountResponse addAccountResponse = new AddAccountResponse();
        addAccountResponse.setId(account.getId());

        return addAccountResponse;
    }

    @Override
    public List<Account> getAccounts() {
        return getAccountMapper().getAccounts();
    }
}
