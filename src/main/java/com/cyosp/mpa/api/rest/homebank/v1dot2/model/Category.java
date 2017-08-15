package com.cyosp.mpa.api.rest.homebank.v1dot2.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by CYOSP on 2017-07-25.
 */
@Getter
@Setter
public class Category {

    @XStreamAsAttribute
    @XStreamAlias("key")
    private int key;

    @XStreamAsAttribute
    @XStreamAlias("parent")
    private int parent;

    @XStreamAsAttribute
    @XStreamAlias("flags")
    private int flags;

    @XStreamAsAttribute
    @XStreamAlias("name")
    private String name;

    //----------------------------------

    @XStreamOmitField
    private BigDecimal balance;

    @XStreamOmitField
    private Currency currency;
}
