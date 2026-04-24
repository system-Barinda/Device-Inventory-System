package com.airtel.Device_inventory_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 1. Disable CSRF for local development with frontend/backend separation
            .csrf().disable()
            
            // 2. Apply CORS configuration
            .cors().configurationSource(corsConfigurationSource())
            .and()
            
            // 3. Ensure a session is created and maintained
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
            
            .authorizeRequests()
                // Publicly accessible paths
                .antMatchers("/", "/login.html", "/api/auth/**", "/css/**", "/js/**", "/*.html").permitAll()
                // All other requests require a valid session
                .anyRequest().authenticated()
            .and()
            
            .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    // CRITICAL: If the request is an API call, return 401 instead of redirecting to login.html
                    // This prevents the frontend from getting HTML when it expects JSON
                    if (request.getRequestURI().startsWith("/api/")) {
                        response.setStatus(401);
                        response.getWriter().write("{\"error\": \"Unauthorized - Session Expired\"}");
                    } else {
                        response.sendRedirect("/login.html");
                    }
                })
            .and()
            
            .logout()
                .logoutUrl("/api/auth/logout")
                .logoutSuccessUrl("/login.html?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Ensure these match your Live Server address exactly
        configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5500", "http://localhost:5500"));
        
        // Methods allowed for the Airtel Inventory System
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Headers allowed in the request
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept", "x-requested-with"));
        
        // CRITICAL: Allows the browser to send JSESSIONID cookies
        configuration.setAllowCredentials(true); 
        
        // Max age for preflight requests
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}