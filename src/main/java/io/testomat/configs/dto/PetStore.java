package io.testomat.configs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PetStore(

        @JsonProperty("apiUrl")
        String apiUrl,

        @JsonProperty("apiKey")
        String apiKey
) {
}
