package com.cyosp.mpa.api.rest.common.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by CYOSP on 2017-08-01.
 */
@RestController
@RequestMapping(ErrorController.PATH)
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    static final String PATH = "/error";

    private final ErrorAttributes errorAttributes;

    @Autowired
    public ErrorController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping
    public Map<String, Object> error(HttpServletRequest request) {
        Map<String, Object> ret;

        WebRequest webRequest = new ServletWebRequest(request);
        ret = errorAttributes.getErrorAttributes(webRequest, false);

        // Return a map only for REST requests
        String path = (String) ret.get("path");
        if (!path.startsWith(CommonController.COMMON_API_PATH)) ret = null;

        return ret;
    }
}
