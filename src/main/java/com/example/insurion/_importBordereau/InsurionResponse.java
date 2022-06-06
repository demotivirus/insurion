package com.example.insurion._importBordereau;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class InsurionResponse {
    private List<ResultImport> resultImport;


    public InsurionResponse(List<ResultImport> resultImport) {
        this.resultImport = resultImport;
    }
}
