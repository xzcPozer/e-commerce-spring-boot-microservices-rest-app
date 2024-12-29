package com.sharf.ecommerce.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Map;

@Configuration
public class FeignConfig {

//    @Bean
//    public RequestInterceptor requestInterceptor() {
//        return requestTemplate -> {
//            Map<String, Collection<String>> headers = requestTemplate.headers();
//            String token = headers.get("Authorization").iterator().next();
//            requestTemplate.header("Authorization", "Bearer " + token);
//        };
//    }
}

