package com.cyosp.mpa.api.rest.homebank.v1dot2.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by CYOSP on 2017-07-26.
 */
@Getter
@Setter
public class OperationResponse {

    private long date;

    private BigDecimal amount;

    private int account;

    private int paymode;

    private int flags;

    private int payee;

    private String wording;

    private int category;
}
