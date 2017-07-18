package com.cyosp.mpa.mapper;

import com.cyosp.mpa.model.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by CYOSP on 2017-07-14.
 */
@Mapper
public interface AccountMapper {

    @Insert("insert into account(name, balance) VALUES( #{name}, #{balance} )")
    @Options(useGeneratedKeys = true)
    int addAccount(Account account);

    @Select("select * from account where id = #{id}")
    Account getAccountById(@Param("id") long id);

    @Select("select * from account")
    List<Account> getAccounts();
}
