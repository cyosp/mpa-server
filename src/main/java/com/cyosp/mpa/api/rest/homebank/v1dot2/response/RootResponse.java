package com.cyosp.mpa.api.rest.homebank.v1dot2.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by CYOSP on 2017-07-16.
 */
@Getter
@Setter
public class RootResponse {

    public static final long ID_NOT_INITIALIZED = -100;
    public static final long ID_DATA_NOT_SAVED = -200;
    public static final long ID_DUPLICATED_NAME = -500;
    public static final long ID_NOT_FOUND = -600;

    private long id = ID_NOT_INITIALIZED;

    private Date ts = null;
}
