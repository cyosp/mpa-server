package com.cyosp.mpa.api.rest.homebank.v1dot2.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by CYOSP on 2017-07-23.
 */
@Getter
@Setter
public class Account {

    @XStreamAsAttribute
    @XStreamAlias("key")
    private int key;

    @XStreamAsAttribute
    @XStreamAlias("flags")
    private int flags;

    @XStreamAsAttribute
    @XStreamAlias("pos")
    private int pos;

    @XStreamAsAttribute
    @XStreamAlias("type")
    private int type;

    @XStreamAsAttribute
    @XStreamAlias("curr")
    private int curr;

    @XStreamAsAttribute
    @XStreamAlias("name")
    private String name;

    @XStreamAsAttribute
    @XStreamAlias("initial")
    private BigDecimal initial;

    @XStreamAsAttribute
    @XStreamAlias("minimum")
    private BigDecimal minimum;

    @XStreamAsAttribute
    @XStreamAlias("cheque1")
    private long cheque1;

    @XStreamAsAttribute
    @XStreamAlias("cheque2")
    private long cheque2;

    //----------------------------------

    @XStreamOmitField
    private BigDecimal balance;

    @XStreamOmitField
    private Options options;

    @XStreamOmitField
    private Currency currency;
}
