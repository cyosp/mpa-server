package com.cyosp.mpa.api.rest.homebank.v1dot2.response;

import com.cyosp.mpa.api.rest.homebank.v1dot2.model.Currency;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by CYOSP on 2017-07-26.
 */
@Getter
@Setter
public class PayeeResponse extends RootResponse {

    private String name;

    private String balance;

    private Currency currency;
}
