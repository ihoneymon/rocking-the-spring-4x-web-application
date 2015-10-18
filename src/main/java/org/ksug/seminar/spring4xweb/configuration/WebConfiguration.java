package org.ksug.seminar.spring4xweb.configuration;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.util.UrlPathHelper;

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

    /**
     * Path Matching v4.0.3
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer
            .setUseSuffixPatternMatch(true)
            .setUseTrailingSlashMatch(false)
            .setUseRegisteredSuffixPatternMatch(true)
            .setPathMatcher(pathMatcher())
            .setUrlPathHelper(urlPathHelper());
    }

    @Bean
    public UrlPathHelper urlPathHelper() {
        return new UrlPathHelper();
    }

    @Bean
    public PathMatcher pathMatcher() {
        return new AntPathMatcher();
    }

}
