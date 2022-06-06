package com.example.insurion._importBordereau;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Passenger {
    private String firstName;
    private String lastName;
    private String middleName;
    private String birthdate;
    private String phoneNumber;
    private String flightNumber;
    private String departureDate;
    private String policyDate;
    private String policyNumber;
    private String currency;
    private int insuranceSum;
    private int delayTimeHours;
    private int firstCountHours;
    private int repeatedCountHours;
    private int firstCountPercent;
    private int repeatedCountPercent;

    private int firstCountSum;
    private int repeatedCountSum;
    private int paymentType;

    private boolean ignoreDepartureTime;
    private boolean override;

    private int productType;
    private String startInsurancePeriod;
    private String finishInsurancePeriod;
    private int cancelSum;
}
