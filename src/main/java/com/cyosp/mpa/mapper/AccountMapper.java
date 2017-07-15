package com.cyosp.mpa.mapper;

import com.cyosp.mpa.model.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by CYOSP on 2017-07-14.
 */
@Mapper
public interface AccountMapper {

    @Insert("insert into account ( name ) VALUES( #{name} )")
    @Options(useGeneratedKeys = true)
    void addAccount(Account account);

    @Select("select * from account")
    List<Account> getAccounts();
}
