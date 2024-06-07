package io.testomat.web.common.data;

public class UserDto {
    public UserDto(String login, String password, boolean rememberMe) {
        this.login = login;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    public String login;
    public String password;
    public boolean rememberMe;

}
