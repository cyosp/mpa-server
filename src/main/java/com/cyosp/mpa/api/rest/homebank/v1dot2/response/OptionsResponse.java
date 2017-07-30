package com.cyosp.mpa.api.rest.homebank.v1dot2.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by CYOSP on 2017-07-29.
 */
@Getter
@Setter
public class OptionsResponse {

    private boolean accountClosed;

    private boolean accountSummaryExclude;

    private boolean accountBudgetExclude;

    private boolean accountReportsExclude;
}
