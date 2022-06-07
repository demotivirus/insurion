package com.example.insurion.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.Duration;


@Configuration
public class InsurionConfig {
//    @Bean
//    public RestTemplate restTemplate() {
//
//        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
//
//        factory.setConnectTimeout(3000);
//        factory.setReadTimeout(3000);
//
//        return new RestTemplate(factory);
//    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {

        return new RestTemplateBuilder();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.setConnectTimeout(Duration.ofMillis(3_000))
                .setReadTimeout(Duration.ofMillis(3_000))
                .build();
    }

//    @Bean
//    public RestTemplate restTemplate() {
//        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
//        factory.setConnectTimeout(3000);
//        factory.setReadTimeout(3000);
//        return new RestTemplate(factory);
//    }

//    @Autowired
//    CloseableHttpClient httpClient;
//
//    @Value("${api.host.baseurl}")
//    private String apiHost;
//
//    @Bean
//    public RestTemplate restTemplate() {
//        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
//        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(apiHost));
//        return restTemplate;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
//        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
//                = new HttpComponentsClientHttpRequestFactory();
//        clientHttpRequestFactory.setHttpClient(httpClient);
//        return clientHttpRequestFactory;
//    }
}
