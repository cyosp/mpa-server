package com.cyosp.mpa.api.rest.homebank.v1dot2.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by CYOSP on 2017-07-25.
 */
@Getter
@Setter
public class Tag {

    @XStreamAsAttribute
    @XStreamAlias("key")
    private int key;

    @XStreamAsAttribute
    @XStreamAlias("name")
    private String name;
}
