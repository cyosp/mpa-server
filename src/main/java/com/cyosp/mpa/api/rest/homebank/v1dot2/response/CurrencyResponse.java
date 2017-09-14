package com.cyosp.mpa.api.rest.homebank.v1dot2.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by CYOSP on 2017-07-26.
 */
@Getter
@Setter
public class CurrencyResponse extends RootResponse {

    private String iso;

    private String name;

    private Character symb;

    private Integer syprf;

    private Character dchar;

    private Character gchar;

    private Integer frac;

    private Integer rate;

    private Integer mdate;
}
