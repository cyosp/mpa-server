package com.cyosp.mpa.api.rest.common.controller;

import com.cyosp.mpa.api.rest.homebank.v1dot2.controller.HomebankRestController;
import com.cyosp.mpa.api.rest.homebank.v1dot2.mapper.XmlMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

        final String DEFAULT_PAGE = "index.html";
        final String STATIC_DIRECTORY = "/static";
        final String FORWARD_STRING = "forward:";
        final String MPA_PREFIX_PATH = "/mpa";

        String personalAccountingManaged = null;

        if (XmlMapper.getHomeBank() != null) personalAccountingManaged = HomebankRestController.SUB_PATH + "/";

        if (personalAccountingManaged != null) {

            try {
                ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(classloader);
                Resource[] resources = resolver.getResources("classpath*:" + STATIC_DIRECTORY + personalAccountingManaged + "**");
                for (int i = 0; i < resources.length; i++) {

                    String uri = resources[i].getURI().toString();
                    String endPath = uri.substring(uri.indexOf(personalAccountingManaged));
                    String urlPath = MPA_PREFIX_PATH + "/" + endPath.replace(personalAccountingManaged, "");
                    String viewName = FORWARD_STRING + endPath;
                    if (uri.endsWith("/")) {
                        urlPath = urlPath.substring(0, urlPath.length() - 1);
                        viewName += DEFAULT_PAGE;
                    }
                    // System.out.println(urlPath + " <=> " + viewName);

                    registry.addViewController(urlPath).setViewName(viewName);
                }

                // Add default home page
                // Example: / => forward:/homebank/v1.2/index.html
                registry.addViewController(MPA_PREFIX_PATH).setViewName(FORWARD_STRING + personalAccountingManaged + DEFAULT_PAGE);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
