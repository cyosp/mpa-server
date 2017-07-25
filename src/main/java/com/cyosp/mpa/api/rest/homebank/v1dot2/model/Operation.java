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
public class Operation {

    @XStreamAsAttribute
    @XStreamAlias("date")
    private long date;

    @XStreamAsAttribute
    @XStreamAlias("amount")
    private BigDecimal amount;

    @XStreamAsAttribute
    @XStreamAlias("account")
    private int accountRef;

    @XStreamAsAttribute
    @XStreamAlias("paymode")
    private int paymentMode;

    @XStreamAsAttribute
    @XStreamAlias("flags")
    private int flags;

    @XStreamAsAttribute
    @XStreamAlias("payee")
    private int payeeRef;

    @XStreamAsAttribute
    @XStreamAlias("wording")
    private String wording;

    @XStreamAsAttribute
    @XStreamAlias("category")
    private int categoryRef;
}
