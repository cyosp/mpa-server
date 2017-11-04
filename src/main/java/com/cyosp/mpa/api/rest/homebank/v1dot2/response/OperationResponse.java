package com.cyosp.mpa.api.rest.homebank.v1dot2.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by CYOSP on 2017-07-26.
 */
@Getter
@Setter
public class OperationResponse extends RootResponse {

    private Long date;

    private String dateFormatted;

    private String amount;

    private String balance;

    private Integer account;

    private Integer paymode;

    private String paymodeName;

    private Integer flags;

    private Integer payee;

    private String payeeName;

    private String wording;

    private Integer category;

    private String categoryName;
}
