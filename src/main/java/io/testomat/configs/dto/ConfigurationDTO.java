package io.testomat.configs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConfigurationDTO(@JsonProperty("petStore") PetStore petStore,
                               @JsonProperty("testomat") Testomat testomat) {
}
