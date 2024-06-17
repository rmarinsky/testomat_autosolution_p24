package io.testomat.api.suite;

import com.github.javafaker.Faker;
import io.testomat.Api.BaseTest;
import io.testomat.Api.controllers.SuitesController;
import io.testomat.Api.dto.suites.Attributes;
import io.testomat.Api.dto.suites.Data;
import io.testomat.Api.dto.suites.TestSuiteDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestSuiteTests extends BaseTest {

    private final String projectName = "dfdfdf";
    private final Faker faker = new Faker();

    private final SuitesController suitesController = new SuitesController(projectName);


    @Test
    @DisplayName("Create and get test suite test")
    void createAndGetTestSuiteTest() {
        var suiteName = faker.book().title();

        var object = suitesController
                .withToken(token)
                .postSuite(getTestSuite(suiteName))
                .toObject();

        System.out.println(object);

        suitesController.getSuites();
    }


    @Test
    @DisplayName("Create and get test suite test")
    void createTestSuiteTest() {
        var suiteName = this.faker.book().title();

        suitesController.withToken(token)
                .postSuite(getTestSuite(suiteName));
    }


    private TestSuiteDto getTestSuite(String suiteName) {
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

}
