package io.testomat.web;


import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public LoginPage loginUser(String mail, String password) {
        $("#content-desktop #user_email").val(mail);
        $("#content-desktop #user_password").val(password);
        $("#content-desktop [name='commit']").click();

        return this;
    }
}
