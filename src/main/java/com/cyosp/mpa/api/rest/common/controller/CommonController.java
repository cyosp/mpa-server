package com.cyosp.mpa.api.rest.common.controller;

import com.cyosp.mpa.api.rest.common.service.CommonService;
import com.cyosp.mpa.api.rest.common.service.Infos;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by CYOSP on 2017-08-01.
 */
@RestController
@RequestMapping(CommonController.COMMON_API_PATH + CommonController.SUB_PATH)
public class CommonController {

    public static final String COMMON_API_PATH = "/mpa/api/rest";

    public static final String SUB_PATH = "/common";

    @Autowired
    @Getter(AccessLevel.PRIVATE)
    CommonService commonService;

    @GetMapping("/infos")
    public Infos getInfos() {
        return getCommonService().getInfos();
    }
}
