package io.testomat.api.dto.suitesJackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.testomat.api.dto.suites.Relationships;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataItem {

    @JsonProperty("relationships")
    private Relationships relationships;

    @JsonProperty("attributes")
    private Attributes attributes;

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

}
