package com.cyosp.mpa.v1.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by CYOSP on 2017-07-14.
 */
@Getter
@Setter
public class AccountResponse extends RootResponse {

    private String name;

    private BigDecimal balance;
}
