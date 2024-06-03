package io.testomat.web;


import io.testomat.web.common.keyboards.KeyboardEmail;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private KeyboardEmail keyboardEmail = new KeyboardEmail();


    public LoginPage loginUser(String mail, String password) {
        $("#content-desktop #user_email").val(mail);
        $("#content-desktop #user_password").val(password);
        $("#content-desktop [name='commit']").click();

        return this;
    }

    public LoginPage fulfillEmail(String email) {
        keyboardEmail.fulfillEmail(email);
        return this;
    }

}
