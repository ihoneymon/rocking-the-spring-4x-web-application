package org.ksug.seminar.spring4xweb.configuration;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
@EnableWebMvc
public class WebConfiguration extends WebMvcAutoConfigurationAdapter {

    /**
     * ViewController 등록
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/error/default").setViewName("error/default");
        registry.addViewController("/error/400").setViewName("error/400");
        registry.addViewController("/error/404").setViewName("error/404");
    }
}
