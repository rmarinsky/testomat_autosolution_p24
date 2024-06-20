package io.testomat.api.login;

import io.testomat.api.controllers.LoginController;
import io.testomat.configs.Config;
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
                .login(Config.get().testomat().apiKey())
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("jwt");
        Assertions.assertThat(actualToken).isNotBlank();
    }

}
