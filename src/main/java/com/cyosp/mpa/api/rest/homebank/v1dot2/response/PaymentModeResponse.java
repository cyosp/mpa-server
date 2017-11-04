package com.cyosp.mpa.api.rest.homebank.v1dot2.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by CYOSP on 2017-11-03.
 */
@Data
@AllArgsConstructor
public class PaymentModeResponse {

    private Integer code;

    private String name;

    private Boolean managed;
}
