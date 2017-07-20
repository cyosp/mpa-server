package com.cyosp.mpa.v1.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by CYOSP on 2017-07-14.
 */
@Getter
@Setter
public class AddAccountRequest {

    @NotNull(message = "error.v1.account.name.null")
    @Size(min = 1, max = 30, message = "error.v1.account.name.size")
    private String name;
}
