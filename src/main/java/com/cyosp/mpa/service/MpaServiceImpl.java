package com.cyosp.mpa.service;

import org.springframework.stereotype.Service;

/**
 * Created by CYOSP on 2017-06-27.
 */
@Service
public class MpaServiceImpl implements MpaService {

    @Override
    public String getAccounts() {
        return "All accounts";
    }
}
