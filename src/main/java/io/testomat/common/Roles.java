package io.testomat.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Roles {

    SIMPLE(new Creds("asdfasd", "passwropd")),
    ADMIN(new Creds("asdfasd", "passwropd")),
    SUPER_ADMIN(new Creds("asdfasd", "passwropd"));

    public final Creds creds;

}
