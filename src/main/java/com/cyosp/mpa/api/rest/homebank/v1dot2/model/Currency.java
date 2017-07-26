package com.cyosp.mpa.api.rest.homebank.v1dot2.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by CYOSP on 2017-07-24.
 */
@Getter
@Setter
public class Currency {

    @XStreamAsAttribute
    @XStreamAlias("key")
    private int key;

    @XStreamAsAttribute
    @XStreamAlias("iso")
    private String iso;

    @XStreamAsAttribute
    @XStreamAlias("name")
    private String name;

    @XStreamAsAttribute
    @XStreamAlias("symb")
    private char symb;

    @XStreamAsAttribute
    @XStreamAlias("syprf")
    private int syprf;

    @XStreamAsAttribute
    @XStreamAlias("dchar")
    private char dchar;

    @XStreamAsAttribute
    @XStreamAlias("gchar")
    private char gchar;

    @XStreamAsAttribute
    @XStreamAlias("frac")
    private int frac;

    @XStreamAsAttribute
    @XStreamAlias("rate")
    private int rate;

    @XStreamAsAttribute
    @XStreamAlias("mdate")
    private int mdate;
}
