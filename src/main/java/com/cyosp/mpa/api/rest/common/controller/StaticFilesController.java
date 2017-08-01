package com.cyosp.mpa.api.rest.common.controller;

import com.cyosp.mpa.api.rest.homebank.v1dot2.controller.HomebankRestController;
import com.cyosp.mpa.api.rest.homebank.v1dot2.mapper.XmlMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * Created by CYOSP on 2017-07-24.
 */
@Controller
public class StaticFilesController implements WebMvcConfigurer {

    /* Allow this type of mapping:
        - /mpa/accounts <=> forward:/homebank/v1.2/accounts/index.html
        - /mpa/css/bootstrap-theme.min.css <=> forward:/homebank/v1.2/css/bootstrap-theme.min.css
        - /mpa/js/bootstrap.min.js <=> forward:/homebank/v1.2/js/bootstrap.min.js
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        String staticDir = "/static";
        String personalAccountingManaged = null;

        if (XmlMapper.getHomeBank() != null) personalAccountingManaged = HomebankRestController.SUB_PATH + "/";

        if (personalAccountingManaged != null) {

            try {
                ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(classloader);
                Resource[] resources = resolver.getResources("classpath*:" + staticDir + personalAccountingManaged + "**");
                for (int i = 0; i < resources.length; i++) {

                    File resourceFile = resources[i].getFile();
                    String endPath = resourceFile.getAbsolutePath().substring(resourceFile.getAbsolutePath().indexOf(personalAccountingManaged));
                    String urlPath = "/mpa/" + endPath.replace(personalAccountingManaged, "");
                    String viewName = "forward:" + endPath;
                    if (resourceFile.isDirectory()) viewName += "/index.html";

                    registry.addViewController(urlPath).setViewName(viewName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
