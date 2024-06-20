package io.testomat.api;

import io.testomat.api.controllers.LoginController;
import io.testomat.configs.Config;
import io.testomat.configs.Envs;

public class BaseTest {

    protected static String token;

    static {
        setEnv(Envs.BETA);
        token = new LoginController()
                .login(Config.get().testomat().apiKey())
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("jwt");
    }

    public static void setEnv(Envs env) {
        System.setProperty("env", env.name());
    }

}
