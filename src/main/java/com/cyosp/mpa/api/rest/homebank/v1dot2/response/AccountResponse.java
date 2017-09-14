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

    private OptionsResponse options;

    private Integer pos;

    private Integer type;

    private CurrencyResponse currency;

    private String name;

    private BigDecimal initial;

    private BigDecimal minimum;

    private Long cheque1;

    private Long cheque2;

    private String balance;
}
