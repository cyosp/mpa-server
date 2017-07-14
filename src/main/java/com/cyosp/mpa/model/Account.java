package com.cyosp.mpa.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by CYOSP on 2017-07-12.
 */
@Getter
@Setter
public class Account extends MpaObject {

    private String name;

    @Override
    public String toString()
    {
        return getName();
    }
}