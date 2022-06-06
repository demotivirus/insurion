package com.example.insurion._importBordereau;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultImport {
    private String flightNumber;
    private String policyNumber;
    private String uuid;
    private List<InsurionErrors> errors;

    public ResultImport(String flightNumber, String policyNumber, String uuid, List<InsurionErrors> errors) {
        this.flightNumber = flightNumber;
        this.policyNumber = policyNumber;
        this.uuid = uuid;
        this.errors = errors;
    }
}
