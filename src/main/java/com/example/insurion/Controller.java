package com.example.insurion;

import com.example.insurion._importBordereau.InsurionResponse;
import com.example.insurion._importBordereau.ListPassangers;
import com.example.insurion._importBordereau.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RequestMapping("/api/v1")
@RestController
public class Controller {
    @Autowired
    private InsurionService insurionService;


    @GetMapping("/")
    public void hello() {

        ListPassangers listPassangers = new ListPassangers();

        List<Passenger> passengers = new ArrayList<>();
        Passenger passenger = Passenger.builder()
                .firstName("Пассажиров")
                .lastName("Пассажир")
                .middleName("Пассажирович")
                .birthdate("11.01.1986")
                .flightNumber("S7-155")
                .phoneNumber("+79161234567")
                .departureDate("2022-06-07 20:00 +03:00")
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
        listPassangers.setRisks(passengers);

        InsurionResponse response = insurionService.saveFlightInInsurion(listPassangers);
        if (response != null) {
            String uuid = response.getResultImport().get(0).getUuid();
            System.out.println(uuid);
        } else {

        }
    }
}
