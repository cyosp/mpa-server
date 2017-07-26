package com.cyosp.mpa.api.rest.homebank.v1dot2.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by CYOSP on 2017-07-26.
 */
@Getter
@Setter
public class CurrencyResponse extends RootResponse {

    private String iso;

    private String name;

    private char symb;

    private int syprf;

    private char dchar;

    private char gchar;

    private int frac;

    private int rate;

    private int mdate;
}
