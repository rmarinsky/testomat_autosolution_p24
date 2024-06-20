package io.testomat.api.controllers;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.testomat.api.common.ResponseDecorator;
import io.testomat.api.dto.suites.TestSuiteDto;
import io.testomat.api.dto.suitesJackson.GetTestSuiteDto;
import io.testomat.api.dto.suitesJackson.PostTestSuiteJResponseDto;

public class SuitesController extends BaseController<SuitesController> {

    private final String projectName;

    public SuitesController(String projectName) {
        this.projectName = projectName;
    }

    private RequestSpecification suiteClient() {
        return baseClient()
                .basePath("/api/" + projectName)
                .contentType(ContentType.JSON);
    }

    public GetTestSuiteDto getSuites() {
        return suiteClient()
                .get("/suites")
                .as(GetTestSuiteDto.class);
    }

    public ResponseDecorator<PostTestSuiteJResponseDto> postSuite(TestSuiteDto testSuiteDto) {
        return new ResponseDecorator<>(
                suiteClient().body(testSuiteDto).post("/suites"),
                200,
                PostTestSuiteJResponseDto.class
        );
    }

    public ResponseDecorator<PostTestSuiteJResponseDto> postSuiteBuilder(TestSuiteDto testSuiteDto) {
        return new ResponseDecorator<PostTestSuiteJResponseDto>()
                .request(suiteClient().body(testSuiteDto).post("/suites"))
                .expectedDefaultStatusCode(200)
                .targetClass(PostTestSuiteJResponseDto.class);
    }

    public ResponseDecorator<PostTestSuiteJResponseDto> postSuiteGenrated(TestSuiteDto targetpostSuiteGenrated) {
        return new ResponseDecorator<PostTestSuiteJResponseDto>()
                .request(suiteClient().body(targetpostSuiteGenrated).post("/suites"))
                .expectedDefaultStatusCode(200)
                .targetClass(PostTestSuiteJResponseDto.class);
    }


    public ResponseDecorator<PostTestSuiteJResponseDto> postSuitesss(String targetPostTestSuiteJResponseDto) {
        return new ResponseDecorator<PostTestSuiteJResponseDto>()
                .request(suiteClient().body(targetPostTestSuiteJResponseDto).post("/suites"))
                .expectedDefaultStatusCode(200)
                .targetClass(PostTestSuiteJResponseDto.class);
    }

}
