package com.cyosp.mpa.api.rest.homebank.v1dot2.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by CYOSP on 2017-07-16.
 */
@Getter
@Setter
public class RootResponse {

    public static final Integer KEY_NOT_INITIALIZED = -100;
    public static final Integer KEY_DATA_NOT_SAVED = -200;
    public static final Integer KEY_DUPLICATED_NAME = -500;
    public static final Integer KEY_NOT_FOUND = -600;

    private Integer key = KEY_NOT_INITIALIZED;
}
