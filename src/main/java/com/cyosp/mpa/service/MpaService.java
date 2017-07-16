package com.cyosp.mpa.service;

import com.cyosp.mpa.exception.DuplicatedNameException;
import com.cyosp.mpa.model.Account;
import com.cyosp.mpa.request.AddAccountRequest;
import com.cyosp.mpa.response.AddAccountResponse;

import java.util.List;

/**
 * Created by CYOSP on 2017-06-27.
 */
public interface MpaService {

    public AddAccountResponse addAccount(AddAccountRequest addAccountRequest) throws DuplicatedNameException;

    public List<Account> getAccounts();
}
