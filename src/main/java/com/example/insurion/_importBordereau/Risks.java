package com.example.insurion._importBordereau;

import com.example.insurion._importBordereau.Passenger;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Risks {
    private List<Passenger> risks;

    public Risks(List<Passenger> risks) {
        this.risks = risks;
    }

    public Risks() {
    }
}
