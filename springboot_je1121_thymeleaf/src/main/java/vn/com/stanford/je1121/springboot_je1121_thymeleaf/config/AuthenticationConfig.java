package vn.com.stanford.je1121.springboot_je1121_thymeleaf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vn.com.stanford.je1121.springboot_je1121_thymeleaf.interceptor.Authorizationinterceptor;

@Configuration
public class AuthenticationConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new Authorizationinterceptor()).addPathPatterns("/admin/**");
    }
}