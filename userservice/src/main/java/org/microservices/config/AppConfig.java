package org.microservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.StandardCharsets;

public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // Add logging interceptor
        restTemplate.getInterceptors().add((request, body, execution) -> {
            // Log the request details
            System.out.println("Request URI: " + request.getURI());
            System.out.println("Request Method: " + request.getMethod());
            System.out.println("Request Body: " + new String(body, StandardCharsets.UTF_8));
            return execution.execute(request, body);
        });
        return restTemplate;
    }
}
