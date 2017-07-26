package com.cyosp.mpa.api.rest.homebank.v1dot2.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by CYOSP on 2017-07-25.
 */
@Getter
@Setter
public class Favorite {

    @XStreamAsAttribute
    @XStreamAlias("amount")
    private BigDecimal amount;

    @XStreamAsAttribute
    @XStreamAlias("account")
    private int account;

    @XStreamAsAttribute
    @XStreamAlias("paymode")
    private int paymode;

    @XStreamAsAttribute
    @XStreamAlias("flags")
    private int flags;

    @XStreamAsAttribute
    @XStreamAlias("payee")
    private int payee;

    @XStreamAsAttribute
    @XStreamAlias("category")
    private int category;

    @XStreamAsAttribute
    @XStreamAlias("wording")
    private String wording;

    @XStreamAsAttribute
    @XStreamAlias("nextdate")
    private long nextdate;

    @XStreamAsAttribute
    @XStreamAlias("every")
    private int every;

    @XStreamAsAttribute
    @XStreamAlias("unit")
    private int unit;

    @XStreamAsAttribute
    @XStreamAlias("limit")
    private int limit;
}
