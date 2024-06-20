package io.testomat.api.controllers;

import io.restassured.specification.RequestSpecification;
import io.testomat.api.common.LogRequestFilter;
import io.testomat.configs.Config;

import static io.restassured.RestAssured.given;

public abstract class BaseController<T> {

    private String targetToken;

    protected RequestSpecification baseClient() {
        var reqSpec = given()
                .baseUri(Config.get().petStore().apiUrl())
                .basePath("/api")
                .filters(new LogRequestFilter());
        if (targetToken != null) {
            reqSpec.header("Authorization", targetToken);
        }
        return reqSpec;
    }

    public void cleanToken() {
        this.targetToken = null;
    }

    public T withToken(String targetToken) {
        this.targetToken = targetToken;
        return (T) this;
    }

}
