package com.cyosp.mpa.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by CYOSP on 2017-07-12.
 */
@Getter
@Setter
public class Account extends MpaObject {

    private String name;

    private BigDecimal balance;
}
