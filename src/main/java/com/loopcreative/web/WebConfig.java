package com.loopcreative.web;

import com.loopcreative.web.interception.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
/*
        registry.addInterceptor(new LoginInterceptor())
                .order(1)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login/join","/admin/login","/admin/login1");
*/

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("http://www.loopcreative.co.kr","http://www.loopcreative.co.kr:3000","http://localhost:3000")
                .allowPrivateNetwork(true)
                .allowedMethods("OPTIONS","GET","POST","PUT","DELETE");

    }

}
