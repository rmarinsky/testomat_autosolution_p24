package io.testomat.api;

import lombok.AllArgsConstructor;
import lombok.Builder;

@lombok.Data
@Builder
@AllArgsConstructor
public class Data{
	private Attributes attributes;
	private String type;
}
