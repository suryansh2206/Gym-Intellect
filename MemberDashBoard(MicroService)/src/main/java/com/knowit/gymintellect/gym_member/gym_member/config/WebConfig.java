package com.knowit.gymintellect.gym_member.gym_member.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.knowit.gymintellect.gym_member.gym_member.interceptor.MembershipAccessInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private MembershipAccessInterceptor membershipAccessInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(membershipAccessInterceptor)
                .addPathPatterns("/member/dashboard/**");
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:3000") // Allow React frontend
        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow methods
        .allowedHeaders("*") // Allow all headers
        .allowCredentials(true); // Allow cookies
    }
}
