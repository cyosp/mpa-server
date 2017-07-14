package com.cyosp.mpa.service;

import com.cyosp.mpa.mapper.AccountMapper;
import com.cyosp.mpa.model.Account;
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

    @Override
    public List<Account> getAccounts() {
        return getAccountMapper().getAccounts();
    }
}
