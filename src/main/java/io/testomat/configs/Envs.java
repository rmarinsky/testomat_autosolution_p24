package io.testomat.configs;

public enum Envs {

    BETA, PROD;

    public static Envs get() {
        var targetEnv = System.getProperty("env"); // -Penv=beta
        return Envs.valueOf(targetEnv);
    }

}
