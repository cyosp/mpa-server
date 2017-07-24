package com.cyosp.mpa.api.rest.core.v1.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by CYOSP on 2017-07-12.
 */
@Getter
@Setter
public class Account extends MpaObject {

    private String name;

    private BigDecimal balance;
}
