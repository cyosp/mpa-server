package com.cyosp.mpa.api.rest.homebank.v1dot2.mapper;

import com.cyosp.mpa.api.rest.homebank.v1dot2.model.Account;
import com.cyosp.mpa.api.rest.homebank.v1dot2.model.HomeBank;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by CYOSP on 2017-08-03.
 */
@UseHomebankDatasource
public interface DbMapper {

    void init();

    @Insert({"INSERT INTO HOMEBANK(v, d) VALUES( #{v}, #{d} )"})
    int addHomebank(HomeBank homeBank);

    @Select("SELECT * FROM HOMEBANK LIMIT 1")
    HomeBank getHomebank();

    @Insert({"INSERT INTO ACCOUNT(key, name) VALUES( #{key}, #{name} )"})
    int addAccount(Account account);

    @Select("SELECT * FROM ACCOUNT")
    List<Account> getAccounts();
}
