package com.cyosp.mpa.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by CYOSP on 2017-07-14.
 */
@Getter
@Setter
public class AddAccountResponse {

    public static final long ID_NOT_INITIALIZED = -1;
    public static final long ID_DUPLICATED_NAME = -2;

    public static final long VERSION_NOT_INITIALIZED = -1;

    private long id = ID_NOT_INITIALIZED;

    private long version = VERSION_NOT_INITIALIZED;

    private BigDecimal balance;
}
