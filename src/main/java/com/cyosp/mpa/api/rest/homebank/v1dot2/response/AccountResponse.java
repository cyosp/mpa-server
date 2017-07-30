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

    private int pos;

    private int type;

    private int curr;

    private String name;

    private BigDecimal initial;

    private BigDecimal minimum;

    private long cheque1;

    private long cheque2;

    private BigDecimal balance;

    private OptionsResponse options;
}
