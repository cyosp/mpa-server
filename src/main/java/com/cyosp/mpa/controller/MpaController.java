package com.cyosp.mpa.controller;

import com.cyosp.mpa.service.MpaService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by CYOSP on 2017-06-27.
 */
@RestController
@RequestMapping("/mpa")
public class MpaController {

    @Autowired
    @Getter
    private MpaService mpaService;

    @GetMapping("/accounts")
    public String getAccounts() {
        return getMpaService().getAccounts();
    }
}
