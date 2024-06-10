package io.testomat.api;

import lombok.AllArgsConstructor;
import lombok.Builder;

@lombok.Data
@Builder
@AllArgsConstructor
public class TestSuiteDto{
	private io.testomat.api.Data data;
}
