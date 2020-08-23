package com.github.syed.bugtracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry){
                String allowedOrigin = "http://localhost:3000";
                registry.addMapping("/projects").allowedOrigins(allowedOrigin);
            }
        };
    }

}
