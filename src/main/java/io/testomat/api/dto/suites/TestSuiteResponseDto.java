package io.testomat.api.dto.suites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestSuiteResponseDto {
    private List<DataItem> data;
    private Meta meta;
}
