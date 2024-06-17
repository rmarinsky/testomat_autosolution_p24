package io.testomat.api;

import io.testomat.Api.controllers.LoginController;

public class BaseTest {

    protected static String token;

    static {
        token = new LoginController()
                .login("testomat_5N5I-BbHMxuXSs28TkaqzBNauPyj5ky63g1718294891")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("jwt");
    }

}
