package io.testomat.api.dto.suitesJackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTestSuiteDto {

    @JsonProperty("data")
    private List<DataItem> data;

    @JsonProperty("meta")
    private Meta meta;

}
