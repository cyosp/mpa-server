package com.cyosp.mpa.v1.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by CYOSP on 2017-07-12.
 */
@Getter
@Setter
public class Operation extends MpaObject {

    private Account account;

    private LocalDate date;

    private Payee payee;

    private BigDecimal amount;

    private Category category;

    private String memo;
}
