package com.cyosp.mpa.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by CYOSP on 2017-07-12.
 */
@Getter
@Setter
public class Account extends MpaObject {

    private String name;

    private BigDecimal balance;

    @Override
    public String toString()
    {
        return getName();
    }
}
