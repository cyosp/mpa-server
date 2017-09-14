package com.cyosp.mpa.api.rest.homebank.v1dot2.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by CYOSP on 2017-07-29.
 */
@Getter
@Setter
public class OptionsResponse {

    private Boolean accountClosed;

    private Boolean accountSummaryExclude;

    private Boolean accountBudgetExclude;

    private Boolean accountReportsExclude;
}
