package com.cyosp.mpa.api.rest.homebank.v1dot2.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by CYOSP on 2017-07-26.
 */
@Getter
@Setter
public class PropertiesResponse {

    private String title;

    private Integer curr;

    private Integer autoSmode;

    private Integer autoWeekday;
}
