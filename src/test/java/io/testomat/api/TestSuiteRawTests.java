package io.testomat.api;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.testomat.Api.dto.suites.Attributes;
import io.testomat.Api.dto.suites.Data;
import io.testomat.Api.dto.suites.TestSuiteDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestSuiteRawTests {

    @Test
    @DisplayName("Login and create test suite in the project")
    void loginAndCreateTestSuiteInTheProject() {
        var jwtToken = getJWTToken();

        String projectId = "dfdfdf";
        String suiteName = new Faker().book().title();

        Response testSuiteResponse = createTestSuite(jwtToken, suiteName, projectId);

        testSuiteResponse
                .then()
                .assertThat()
                .statusCode(200)
                .body("data.attributes.title", equalTo(suiteName))
                .body("data.attributes.file-type", equalTo("file"));
    }

    @Test
    @DisplayName("Login and create test suite in the project DTO")
    void loginAndCreateTestSuiteInTheProjectWithDTO() {
        var jwtToken = getJWTToken();

        String projectId = "dfdfdf";
        var suiteName = getTestSuite(new Faker().book().title());

        Response testSuiteResponse = createTestSuiteDTO(jwtToken, suiteName, projectId);

        testSuiteResponse
                .then()
                .statusCode(200)
                .body("data.attributes.title", equalTo(suiteName.getData().getAttributes().getTitle()))
                .body("data.attributes.file-type", equalTo("file"));
    }


    private static Response createTestSuite(String jwtToken, String suiteName, String projectId) {
        return getRequestSpecification()
                .header("Authorization", jwtToken)
                .contentType(ContentType.JSON)
                .body("{\"data\":{\"attributes\":" +
                        String.format("{\"title\":\"%s\",", suiteName) +
                        "\"file-type\":\"file\"," +
                        "\"is-root\":false," +
                        "\"sync\":false," +
                        "\"test-count\":null," +
                        "\"position\":null," +
                        "\"file\":null," +
                        "\"created-at\":null," +
                        "\"updated-at\":null" +
                        "}," +
                        "\"type\":\"suites\"" +
                        "}}")
                .post("/{project_id}/suites", projectId);

    }

    private static Response createTestSuiteDTO(String jwtToken, TestSuiteDto testSuite, String projectId) {
        return getRequestSpecification()
                .header("Authorization", jwtToken)
                .contentType(ContentType.JSON)
                .body(testSuite)
                .when()
                .post("/{project_id}/suites", projectId);

    }

    private static TestSuiteDto getTestSuite(String suiteName) {
        return TestSuiteDto.builder()
                .data(Data.builder()
                        .attributes(Attributes.builder()
                                .fileType("file").title(suiteName)
                                .isRoot(false)
                                .sync(false)
                                .build()
                        ).type("suites")
                        .build()
                ).build();
    }

    private static String getJWTToken() {
        return getRequestSpecification()
                .contentType(ContentType.URLENC)
                .formParam("api_token", "testomat_MwMPpmF7fgLvh-xAdlzhKdyMU57zF2xM0A1718034222")
                .post("/login")
                .jsonPath()
                .getString("jwt");
    }

    private static RequestSpecification getRequestSpecification() {
        return given()
                .baseUri("https://beta.testomat.io")
                .basePath("/api")
                .log().all();
    }

}
