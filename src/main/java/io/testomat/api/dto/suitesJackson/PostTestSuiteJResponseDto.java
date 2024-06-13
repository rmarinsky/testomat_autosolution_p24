package io.testomat.api.dto.suitesJackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostTestSuiteJResponseDto {

    @JsonProperty("data")
    private DataItem data;

    @JsonProperty("meta")
    private Meta meta;
}
