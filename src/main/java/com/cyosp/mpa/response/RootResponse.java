package com.cyosp.mpa.response;

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
    public static final long ID_LINE_NOT_INSERTED = -200;
    public static final long ID_DUPLICATED_NAME = -300;

    private long id = ID_NOT_INITIALIZED;

    private Date ts = null;
}
