package com.example.insurion;

import com.example.insurion._importBordereau.InsurionResponse;
import com.example.insurion._importBordereau.ListPassangers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class InsurionService {
    @Value("${insurion.importBordereau.url}")
    private String importBordereauUrl;

    @Value("${insurion.importBordereau.pass}")
    private String importBordereauPass;

    @Value("${insurion.importBordereau.partner_code}")
    private String importBordereauPartnerCode;

    @Autowired
    RestTemplate restTemplate;


    public InsurionResponse saveFlightInInsurion(ListPassangers listPassangers) {
        //String URL = "https://env-6655618.jelastic.regruhosting.ru/api/v2/importBordereau?partnerCode=312&sign=0c673aa31841703ffe1df0d76a2b00f7fee3f0eed7277a176e1082e6070430f4";
        ObjectMapper mapper = new ObjectMapper();
        String requestToJson = "";
        try {
            requestToJson = mapper.writeValueAsString(listPassangers);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String sha256 = getSha256(requestToJson.concat(importBordereauPass));

        StringBuilder URL = new StringBuilder()
                .append(importBordereauUrl)
                .append("?partnerCode=")
                .append(importBordereauPartnerCode)
                .append("&sign=")
                .append(sha256);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");

        InsurionResponse response = null;
        ResponseEntity<InsurionResponse> responseEntity = restTemplate.exchange(URL.toString(), HttpMethod.POST, new HttpEntity<>(listPassangers), InsurionResponse.class);
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            if (responseEntity.getBody().getResultImport().get(0).getErrors() == null) {
                response = responseEntity.getBody();
            }
            else {
                log.info("Insurion return errors: {}", responseEntity.getBody().getResultImport().get(0).getErrors());
            }
        }
        else {
            log.info("Insurion return another http status code: {}", responseEntity.getStatusCode());
        }

        log.info("Insurion response: {}", response);
        return response;
    }

    private String getSha256(String base) {
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
