package io.testomat.api.dto.suites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Meta {
    private Integer perPage;
    private Integer num;
    private Integer totalPages;
    private Integer page;
}
