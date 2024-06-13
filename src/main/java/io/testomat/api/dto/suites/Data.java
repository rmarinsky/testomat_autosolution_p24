package io.testomat.api.dto.suites;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Data {
    @JsonProperty("id")
    private String id;
    private Attributes attributes;
    private String type;
}
