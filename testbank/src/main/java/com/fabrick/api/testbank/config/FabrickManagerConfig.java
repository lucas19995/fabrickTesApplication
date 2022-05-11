package com.fabrick.api.testbank.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import java.time.Duration;

@Configuration
public class FabrickManagerConfig {
        @Value("${local.bancasella.timeout}")
        private int connectTimeout;

        @Value("${local.bancasella.readtimeout}")
        private int readTimeout;

        @Bean
        public RestTemplate restTemplate(RestTemplateBuilder builder) {

            return builder
                    .setConnectTimeout(Duration.ofMillis(connectTimeout))
                    .setReadTimeout(Duration.ofMillis(readTimeout))
                    .build();
        }
}
