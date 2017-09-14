package com.cyosp.mpa.api.rest.homebank.v1dot2.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by CYOSP on 2017-07-26.
 */
@Getter
@Setter
public class FavoriteResponse {

    private BigDecimal amount;

    private Integer account;

    private Integer paymode;

    private Integer flags;

    private Integer payee;

    private Integer category;

    private String wording;

    private Long nextdate;

    private Integer every;

    private Integer unit;

    private Integer limit;
}
