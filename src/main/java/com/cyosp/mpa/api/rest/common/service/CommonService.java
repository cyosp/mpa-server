package com.cyosp.mpa.api.rest.common.service;

import com.cyosp.mpa.api.rest.common.model.Infos;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by CYOSP on 2017-09-05.
 */
@Service
public class CommonService {

    @Value("${mpa.version}")
    String version;

    public Infos getInfos() {
        Infos ret = new Infos();
        ret.setVersion(version);
        return ret;
    }
}
