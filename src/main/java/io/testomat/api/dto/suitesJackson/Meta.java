package io.testomat.api.dto.suitesJackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Meta {

    @JsonProperty("per_page")
    private Integer perPage;

    @JsonProperty("num")
    private Integer num;

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("page")
    private Integer page;
}
