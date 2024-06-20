package io.testomat.api.controllers;

import io.restassured.response.Response;

public class UserController extends BaseController<UserController> {

    public Response getUsers() {
        return baseClient()
                .get("/users");
    }


}
