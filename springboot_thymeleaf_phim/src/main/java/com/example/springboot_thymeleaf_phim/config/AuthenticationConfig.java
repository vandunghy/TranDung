package com.example.springboot_thymeleaf_phim.config;

import com.example.springboot_thymeleaf_phim.interceptor.Authorizationinterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class AuthenticationConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new Authorizationinterceptor()).addPathPatterns("/call-api/**");
    }
}