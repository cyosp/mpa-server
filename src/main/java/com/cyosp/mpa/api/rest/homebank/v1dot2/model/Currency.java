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
    private Integer key;

    @XStreamAsAttribute
    @XStreamAlias("iso")
    private String iso;

    @XStreamAsAttribute
    @XStreamAlias("name")
    private String name;

    @XStreamAsAttribute
    @XStreamAlias("symb")
    private Character symb;

    @XStreamAsAttribute
    @XStreamAlias("syprf")
    private Integer syprf;

    @XStreamAsAttribute
    @XStreamAlias("dchar")
    private Character dchar;

    @XStreamAsAttribute
    @XStreamAlias("gchar")
    private Character gchar;

    @XStreamAsAttribute
    @XStreamAlias("frac")
    private Integer frac;

    @XStreamAsAttribute
    @XStreamAlias("rate")
    private Integer rate;

    @XStreamAsAttribute
    @XStreamAlias("mdate")
    private Integer mdate;
}
