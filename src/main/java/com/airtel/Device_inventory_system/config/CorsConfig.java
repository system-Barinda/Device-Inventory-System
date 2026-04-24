package com.airtel.Device_inventory_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**")
                        // ✅ Use PATTERNS instead of allowedOrigins (fix error)
                        .allowedOriginPatterns("http://127.0.0.1:5500", "http://localhost:5500")
                        
                        // ✅ Allow all HTTP methods
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        
                        // ✅ Allow all headers
                        .allowedHeaders("*")
                        
                        // ✅ IMPORTANT for sessions / cookies
                        .allowCredentials(true);
            }
        };
    }
}