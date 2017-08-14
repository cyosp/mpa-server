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

    private String dateFormatted;

    private String amount;

    private String balance;

    private int account;

    private int paymode;

    private int flags;

    private int payee;

    private String payeeName;

    private String wording;

    private int category;

    private String categoryName;
}
