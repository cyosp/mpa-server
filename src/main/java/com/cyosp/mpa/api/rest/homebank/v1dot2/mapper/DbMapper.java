package com.cyosp.mpa.api.rest.homebank.v1dot2.mapper;

import com.cyosp.mpa.api.rest.homebank.v1dot2.model.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by CYOSP on 2017-08-03.
 */
@UseHomebankDatasource
public interface DbMapper {


    @Insert({"DROP TABLE ACCOUNT IF EXISTS; CREATE TABLE IF NOT EXISTS account(key INT PRIMARY KEY, name VARCHAR UNIQUE)"})
    void init();

    @Insert({"insert into account(key, name) VALUES( #{key}, #{name} )"})
    int addAccount(Account account);

    @Select("select * from account")
    List<Account> getAccounts();
}
