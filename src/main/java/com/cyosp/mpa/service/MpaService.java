package com.cyosp.mpa.service;

import com.cyosp.mpa.exception.DuplicatedNameException;
import com.cyosp.mpa.exception.LineNotInsertedException;
import com.cyosp.mpa.request.AddAccountRequest;
import com.cyosp.mpa.response.AccountResponse;

import java.util.List;

/**
 * Created by CYOSP on 2017-06-27.
 */
public interface MpaService {

    public AccountResponse addAccount(AddAccountRequest addAccountRequest) throws LineNotInsertedException, DuplicatedNameException;

    public List<AccountResponse> getAccounts();
}
