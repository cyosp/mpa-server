package com.cyosp.mpa.request;

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

    @NotNull(message = "error.account.name.null")
    @Size(min = 1, max = 30, message = "error.account.name.size")
    private String name;
}
