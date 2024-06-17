package io.testomat.common

import io.restassured.response.Response
import org.assertj.core.api.Assertions

data class ResponseDecorator<T>(
    val targetResponse: Response,
    val defaultStatusCode: HTTPStatusCodes,
    val targetObject: Class<T>
) {

    fun expectedStatusCodeIs(expectedStatusCode: HTTPStatusCodes): ResponseDecorator<T> {
        targetResponse.expectedStatusCodeIs(expectedStatusCode)
        return this
    }

    inline fun <reified T> toObject(): T {
        Assertions.assertThat(targetResponse.statusCode()).isEqualTo(defaultStatusCode)
        try {
            return this.targetResponse.toReified<T>()
        } catch (unEx: Exception) {
            throw catchKResponseException(unEx, T::class.java)
        }
    }
}

inline fun <reified T> Response.toReified(): T {
    try {
        return this.body().`as`(T::class.java)
    } catch (unEx: Exception) {
        throw catchKResponseException(unEx, T::class.java)
    }
}


enum class HTTPStatusCodes(val code: Int) {
    OK(200),
    CREATED(201),
    ACCEPTED(202),
    NO_CONTENT(204),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405),
    CONFLICT(409),
    UNAVAILABLE_FOR_LEGAL_REASONS(451),
    INTERNAL_SERVER_ERROR(500),
    BAD_GATEWAY(502),
    SERVICE_UNAVAILABLE(503),
    GATEWAY_TIMEOUT(504),
}
