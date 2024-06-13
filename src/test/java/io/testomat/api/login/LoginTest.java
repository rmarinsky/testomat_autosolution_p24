package io.testomat.api.login;

import io.testomat.api.controllers.LoginController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTest {


    @Test
    @DisplayName("Invalid login api token test")
    void invalidLoginApiTokenTest() {
        new LoginController().login("invalid_token").then().statusCode(401);
    }

    @Test
    @DisplayName("Valid token tests")
    void validTokenTests() {
        String actualToken = new LoginController()
                .login("testomat_MwMPpmF7fgLvh-xAdlzhKdyMU57zF2xM0A1718034222")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("jwt");
        Assertions.assertThat(actualToken).isNotBlank();
    }

}
