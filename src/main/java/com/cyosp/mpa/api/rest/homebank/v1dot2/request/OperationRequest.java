package com.cyosp.mpa.api.rest.homebank.v1dot2.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by CYOSP on 2017-08-20.
 */
@Getter
@Setter
public class OperationRequest {

    //@NotNull(message = "error.v1.account.name.null")
    //@Size(min = 1, max = 30, message = "error.v1.account.name.size")
    private Date date;

    private Integer paymode;

    private String payee;

    private BigDecimal amount;

    private String category;

    private String wording;
}
