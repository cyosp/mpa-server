package com.cyosp.mpa.api.rest.homebank.v1dot2.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by CYOSP on 2017-07-26.
 */
@Getter
@Setter
public class CategoryResponse extends RootResponse {

    private String name;

    private BigDecimal balance;
}
