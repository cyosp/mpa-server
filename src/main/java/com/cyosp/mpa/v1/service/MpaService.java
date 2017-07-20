package com.cyosp.mpa.v1.service;

import com.cyosp.mpa.v1.exception.DuplicatedNameException;
import com.cyosp.mpa.v1.exception.LineNotDeletedException;
import com.cyosp.mpa.v1.exception.LineNotInsertedException;
import com.cyosp.mpa.v1.exception.LineNotUpdatedException;
import com.cyosp.mpa.v1.request.AccountRequest;
import com.cyosp.mpa.v1.response.AccountResponse;

import java.util.List;

/**
 * Created by CYOSP on 2017-06-27.
 */
public interface MpaService {

    public AccountResponse addAccount(AccountRequest accountRequest) throws LineNotInsertedException, DuplicatedNameException;

    public List<AccountResponse> getAccounts();

    public AccountResponse getAccountById(long id);

    public AccountResponse updateAccount(long id, AccountRequest accountRequest) throws LineNotUpdatedException, DuplicatedNameException;

    public AccountResponse deleteAccount(long id) throws LineNotDeletedException;
}
