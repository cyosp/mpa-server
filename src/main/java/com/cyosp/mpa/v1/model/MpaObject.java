package com.cyosp.mpa.v1.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


/**
 * Created by CYOSP on 2017-07-12.
 */
@Getter
@Setter
public class MpaObject {

    private long id;

    private Date ts;

    private BigDecimal balance;
}
