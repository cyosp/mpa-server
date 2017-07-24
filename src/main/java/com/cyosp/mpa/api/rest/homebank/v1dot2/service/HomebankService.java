package com.cyosp.mpa.api.rest.homebank.v1dot2.service;

import com.cyosp.mpa.api.rest.common.exception.*;
import com.cyosp.mpa.api.rest.homebank.v1dot2.request.AccountRequest;
import com.cyosp.mpa.api.rest.homebank.v1dot2.response.AccountResponse;

import java.util.List;

/**
 * Created by CYOSP on 2017-06-27.
 */
public interface HomebankService {

    public void reload();

    public AccountResponse addAccount(AccountRequest accountRequest) throws DataNotSavedException, DuplicatedNameException;

    public List<AccountResponse> getAccounts();

    public AccountResponse getAccountById(long id);

    public AccountResponse updateAccount(long id, AccountRequest accountRequest) throws LineNotUpdatedException, DuplicatedNameException;

    public AccountResponse deleteAccount(long id) throws LineNotDeletedException;
}
