package io.testomat.configs;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TestomatEnvs { //just for example how to separate enums

    LOCAL("https://localhost"), BETA("https://beta.testomat.io");

    public final String domain;

}
