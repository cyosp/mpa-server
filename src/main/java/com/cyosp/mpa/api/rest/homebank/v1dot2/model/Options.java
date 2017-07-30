package com.cyosp.mpa.api.rest.homebank.v1dot2.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by CYOSP on 2017-07-29.
 */
@Getter
@Setter
public class Options {

    public static final int IS_ACCOUNT_CLOSED_BITMASK = 0x02;
    private boolean accountClosed;

    public static final int IS_ACCOUNT_SUMMARY_EXCLUDE_BITMASK = 0x10;
    private boolean accountSummaryExclude;

    public static final int IS_ACCOUNT_BUDGET_EXCLUDE_BITMASK = 0x20;
    private boolean accountBudgetExclude;

    public static final int IS_ACCOUNT_REPORTS_EXCLUDE_BITMASK = 0x40;
    private boolean accountReportsExclude;

    public void setOptions(int decimalOptionsValue) {
        accountClosed = (decimalOptionsValue & IS_ACCOUNT_CLOSED_BITMASK) > 0;

        accountSummaryExclude = (decimalOptionsValue & IS_ACCOUNT_SUMMARY_EXCLUDE_BITMASK) > 0;

        accountBudgetExclude = (decimalOptionsValue & IS_ACCOUNT_BUDGET_EXCLUDE_BITMASK) > 0;

        accountReportsExclude = (decimalOptionsValue & IS_ACCOUNT_REPORTS_EXCLUDE_BITMASK) > 0;
    }
}
