package com.example.personalfinance;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/Profile-pictures/**")
                .addResourceLocations("file:/C:/Users/Sebastian/Desktop/Java project/Profile-pictures/");
    }
}

