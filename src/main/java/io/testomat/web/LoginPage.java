package io.testomat.web;


import com.codeborne.selenide.Configuration;
import io.testomat.common.Roles;
import io.testomat.web.common.data.UserDto;
import io.testomat.web.common.keyboards.KeyboardEmail;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final UserDto userDto;
    public final KeyboardEmail keyboardEmail = new KeyboardEmail();

    public LoginPage(UserDto userDto) {
        this.userDto = userDto;
    }

    public LoginPage loginUser() {
        $("#content-desktop #user_email").val(this.userDto.login);

        Configuration.fastSetValue = true;
        $("#content-desktop #user_password").val(this.userDto.password);
        Configuration.fastSetValue = false;

        $("#content-desktop [name='commit']").click();

        Configuration.clickViaJs = true;
        if (this.userDto.rememberMe) {
            $("[for='user_remember_me']").click();
        }
        Configuration.clickViaJs = false;
        return this;
    }

    public LoginPage loginRole(Roles role) {

        $("#content-desktop #user_email").val(role.creds.username());

        Configuration.fastSetValue = true;
        $("#content-desktop #user_password").val(role.creds.password());
        Configuration.fastSetValue = false;

        $("#content-desktop [name='commit']").click();

        Configuration.clickViaJs = true;
        if (this.userDto.rememberMe) {
            $("[for='user_remember_me']").click();
        }
        Configuration.clickViaJs = false;
        return this;
    }

    public LoginPage fulfillEmail(String email) {
        keyboardEmail.fulfillEmail(email);
        return this;
    }

}
