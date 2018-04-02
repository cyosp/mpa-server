package com.cyosp.mpa.api.rest.homebank.v1dot2.repository;

import com.cyosp.mpa.api.rest.common.exception.DataNotSavedException;
import com.cyosp.mpa.api.rest.common.exception.DuplicatedNameException;
import com.cyosp.mpa.api.rest.homebank.v1dot2.model.*;
import com.cyosp.mpa.api.rest.homebank.v1dot2.request.OperationRequest;
import com.cyosp.mpa.api.rest.homebank.v1dot2.response.OperationResponse;

import java.util.List;

/**
 * Created by CYOSP on 2018-04-02.
 */
public interface HomebankRepository {

    void load();

    HomeBank getInfos();

    Properties getProperties();

    int addAccount(Account account) throws DuplicatedNameException, DataNotSavedException;

    List<Account> getAccounts();

    List<Operation> getOperationsByAccount(int id);

    List<Category> getCategoriesByAccount(int id);

    List<Payee> getPayeesByAccount(int id);

    OperationResponse addOperationByAccount(int id, OperationRequest operationRequest) throws DataNotSavedException;

    List<Currency> getCurrencies();

    List<Favorite> getFavorites();

    List<Operation> getOperations();

    List<Tag> getTags();
}
