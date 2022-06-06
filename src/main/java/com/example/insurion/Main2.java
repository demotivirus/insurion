package com.example.insurion;

import com.example.insurion._importBordereau.InsurionResponse;
import com.example.insurion._importBordereau.Passenger;
import com.example.insurion._importBordereau.Risks;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {

        Risks risks = new Risks();

        List<Passenger> passengers = new ArrayList<>();
        Passenger passenger = Passenger.builder()
                .firstName("Пассажиров")
                .lastName("Пассажир")
                .middleName("Пассажирович")
                .birthdate("11.01.1986")
                .flightNumber("S7-155")
                .phoneNumber("+79161234567")
                .departureDate("2022-05-06 20:00 +03:00")
                .policyNumber("policy_number1")
                .currency("RUB")
                .insuranceSum(12000)
                .delayTimeHours(2)
                .firstCountHours(2)
                .repeatedCountHours(1)
                .firstCountPercent(10)
                .repeatedCountPercent(30)
                .paymentType(1)
                .override(true).build();
        passengers.add(passenger);

        risks.setRisks(passengers);

        String URL = "https://env-6655618.jelastic.regruhosting.ru/api/v2/importBordereau?partnerCode=312&sign=0c673aa31841703ffe1df0d76a2b00f7fee3f0eed7277a176e1082e6070430f4";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        ObjectMapper mapper = new ObjectMapper();
        String s = "";
        try {
            s = mapper.writeValueAsString(risks);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ResponseEntity<InsurionResponse> response2 = restTemplate.exchange(URL, HttpMethod.POST, new HttpEntity<>(risks), InsurionResponse.class);
        System.out.println(response2);
    }
}
