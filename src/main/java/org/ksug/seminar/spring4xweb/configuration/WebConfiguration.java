package org.ksug.seminar.spring4xweb.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer;
import org.springframework.web.util.UrlPathHelper;

@Configuration
@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurerAdapter {

    /**
     * ViewController 등록
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/error/default").setViewName(
                "static/templates/error/default.html");
        registry.addViewController("/error/400").setViewName(
                "static/templates/error/400.html");
        registry.addViewController("/error/404").setViewName(
                "static/templates/error/404.html");
        registry.addViewController("/home").setViewName("redirect:/");
    }

    /**
     * Path Matching v4.0.3
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(true)
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

    /**
     * Global CORS Configuration
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 간략설정
        registry.addMapping("/**");

        // 상세설정
        registry.addMapping("/api/**").allowedOrigins("http://domain2.com")
                .allowedMethods("PUT", "DELETE")
                .allowedHeaders("header1", "header2", "header3")
                .exposedHeaders("header1", "header2").allowCredentials(false)
                .maxAge(3600);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.scriptTemplate();
    }

    @Bean
    public ScriptTemplateConfigurer scriptTemplateConfigurer() {
        ScriptTemplateConfigurer configurer = new ScriptTemplateConfigurer();
        configurer.setEngineName("nashorn");
        /**
         * pollyfill.js: 오래된 브라우저에서 최신기능(HTML5) 등을 작동 가능하도록 만드는 기술 브라우저의 특성을 사용할
         * 자바스크립트 라이브러리 코드가 나즈혼에서 에러가 발생하지 않도록 설정한 파일
         * 
         * 나즈혼에는 window 객체가 없다.
         * 
         * polyfill은 사용하는 자바스크립트 라이브러리에 따라 달라진다.
         * 
         * 자바스크립트는 순서가 중요하기 때문에 다음과 같은 형태로 가져다 쓸 수밖에 없게 된다.
         */
        configurer.setScripts("/static/js/polyfill.js",
                "/META-INF/resources/webjars/handlebars/4.0.2/handlebars.js",
                "/static/js/render.js");
        configurer.setRenderFunction("render");
        /**
         * 나즈혼은 스레드 세이프하지 않게 설계되어 있다. 스크립트 템플릿은 뷰 및 엔진을 재활용하여 사용한다.
         * 
         * handlebar, react 등은 스레드세이프 하지 않기 때문에 (전역변수 사용) false로 설정해야 한다. 전역변수
         * 선언을 끈다.
         * 
         * 자바스크립트 템플릿 라이브러리가 스레드 세이프한지 확인후 사용해야 한다. mustache 는 스레드세이프하다.
         * 
         * 첫 리퀘스트는 느리다. 반복 호출에는 속도가 빨라진다.
         */
        configurer.setSharedEngine(false);
        return configurer;
    }

    /**
     * 정적자원에 대한 캐시제어를 전역적으로 설정
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/resources/webjars/")
                    .setCacheControl(
                            CacheControl.maxAge(1, TimeUnit.HOURS)
                                    .cachePublic());
        }
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**")
                    .addResourceLocations("classpath:/static")
                    .setCacheControl(
                            CacheControl.maxAge(0, TimeUnit.SECONDS)
                                    .cachePublic());
        }
    }
}
