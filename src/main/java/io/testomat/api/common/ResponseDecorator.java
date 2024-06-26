package io.testomat.api.common;

import io.restassured.response.Response;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;

@NoArgsConstructor
public class ResponseDecorator<T> {

    private Response targetResponse;
    private int expectedDefaultStatusCode;
    private Class<T> targetClass;

    public ResponseDecorator(Response targetResponse, int expectedDefaultStatusCode, Class<T> targetClass) {
        this.targetResponse = targetResponse;
        this.expectedDefaultStatusCode = expectedDefaultStatusCode;
        this.targetClass = targetClass;
    }

    public ResponseDecorator assertStatusCode(int statusCode) {
        Assertions.assertThat(targetResponse.statusCode()).withFailMessage(
                String.format(
                        "Expected status code %s, but was %s \nresponse body was %s",
                        statusCode,
                        targetResponse.statusCode(),
                        targetResponse.body().asPrettyString()
                )
        ).isEqualTo(statusCode);
        return this;
    }

    @SneakyThrows
    public <T> T toObject() {
        assertStatusCode(expectedDefaultStatusCode);
        try {
            return (T) as(this.targetClass);
        } catch (Exception e) {
            throw BindingExceptionHandler.catchResponseException(e, targetClass);
        }
    }

    public <T> T as(Class<T> targetClass) {
        return targetResponse.as(targetClass);
    }

    public ResponseDecorator<T> request(Response targetResponse) {
        this.targetResponse = targetResponse;
        return this;
    }

    public ResponseDecorator<T> expectedDefaultStatusCode(int expectedDefaultStatusCode) {
        this.expectedDefaultStatusCode = expectedDefaultStatusCode;
        return this;
    }

    public ResponseDecorator<T> targetClass(Class<T> targetClass) {
        this.targetClass = targetClass;
        return this;
    }

}
