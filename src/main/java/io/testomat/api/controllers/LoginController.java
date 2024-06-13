package io.testomat.api.controllers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginController extends BaseController<LoginController> {

    public Response login(String targetToken) {
        return baseClient()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("api_token", targetToken)
                .post("/login");
    }

}
