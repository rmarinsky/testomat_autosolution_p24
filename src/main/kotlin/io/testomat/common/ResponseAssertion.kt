package io.testomat.common

import io.restassured.response.Response
import org.assertj.core.api.Assertions
import java.io.File
import java.nio.file.Files
import java.sql.SQLException
import java.util.*

fun <T> catchKResponseException(unEx: Exception, t: Class<T>): Throwable {
    unEx.toString().apply {
        if (contains("data not found", true)) {
            throw AssertionError(
                "Failed to parse response body to ${t.name},\n" +
                        "service was responded with 404 status code in the payload"
            )
        }
        if (contains("Ой, сталась помилка!", true) || contains("Oops, an error occurred", true)) {
            throw AssertionError(
                "Failed to parse response body to ${t.name}, service was failed with 500 status code"
            )
        } else if (contains("Unrecognized field", true)) {
            val unrecognizedField = substringBefore("\" (class").substringAfter("Unrecognized field \"")
            throw NoSuchFieldException(
                "$unrecognizedField add line of code:\n " +
                        "\tval $unrecognizedField: String?\n" +
                        "\tat ${unrecognizedField.getLineOfCodeOfClassVariable(t.name)}"
            )
        } else if (contains("MissingKotlinParameterException", true)) {
            val nullableField = substringAfter("(therefore NULL) value for creator parameter ")
                .substringBefore(" which is a non-nullable type")
            throw InvalidPropertiesFormatException(
                "Can't instantiate non-nullable '$nullableField', add ? to the filed type\n"
                        + "\tat ${nullableField.getLineOfCodeOfClassVariable(t.name)}"
            )
        } else if (contains("SQL", true)) {
            throw SQLException(
                "syntax or lock exception on the service"
            )
        } else throw unEx
    }
}


fun String.getLineOfCodeOfClassVariable(classPackagePath: String): String {
    val className = classPackagePath.substringAfterLast(".") + ".kt"

    val startDir = File(System.getProperty("user.dir"))

    val file = Files.walk(startDir.toPath())
        .filter { it.toFile().isFile && it.fileName.toString().contains(className) }
        .findFirst()
        .orElse(null)?.toFile()

    val variable = Regex("""^\s*val\s+$this\s*:\s*[A-Z]""")

    var lineNumberCounter = 0
    file?.readLines()?.forEach { line ->
        lineNumberCounter++
        if (line.contains(variable)) {
            return "$classPackagePath($className:$lineNumberCounter)"
        }
    }
    return "$classPackagePath($className:1)"
}


fun Response.expectedStatusCodeIs(expectedStatusCode: HTTPStatusCodes): Response {
    Assertions
        .assertThat(this.statusCode)
        .withFailMessage(expectedStatusCodeMessage(expectedStatusCode, this))
        .isEqualTo(expectedStatusCode.code)
    return this
}


private fun expectedStatusCodeMessage(expectedStatus: HTTPStatusCodes, response: Response): String {
    fun namedCode(response: Response): String {
        val responseCode = response.statusCode()
        val name = HTTPStatusCodes.values().find { it.code == responseCode }?.name ?: responseCode
        return "$responseCode - ($name)"
    }

    return "\n" +
            "Expected status code '${expectedStatus.code} - ($expectedStatus)' but was '${namedCode(response)}'\n" +
            "Actual body: \n${minimizeResponse(response)}"
}


private fun minimizeResponse(response: Response): String {
    response.asPrettyString().let {
        return if (it.contains("SQL", true)) { //for SQLGrammarException exceptions
            it.substringBefore("SQL")
        } else {
            it
        }
    }
}
