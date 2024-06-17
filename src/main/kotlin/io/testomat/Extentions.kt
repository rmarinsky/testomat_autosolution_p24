package io.testomat

import io.restassured.response.Response
import org.assertj.core.api.AbstractStringAssert
import org.assertj.core.api.Assertions

fun String.assertString(): AbstractStringAssert<*> = Assertions.assertThat(this)

fun Response.assertBodyAsString() = this.body.asString().assertString()

