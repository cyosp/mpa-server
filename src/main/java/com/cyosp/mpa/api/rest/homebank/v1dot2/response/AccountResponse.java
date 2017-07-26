package com.cyosp.mpa.api.rest.homebank.v1dot2.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by CYOSP on 2017-07-14.
 */
@Getter
@Setter
public class AccountResponse extends RootResponse {

    private int flags;

    private int position;

    private int type;

    private int currencyRef;

    private String name;

    private BigDecimal initialBalance;

    private BigDecimal minimumBalance;

    private long cheque1;

    private long cheque2;

    private BigDecimal balance;
}
