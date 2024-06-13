package io.testomat.api.dto.suites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataItem {
    private Relationships relationships;
    private Attributes attributes;
    private String id;
    private String type;
}
