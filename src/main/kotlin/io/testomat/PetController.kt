package io.testomat

import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification
import io.testomat.common.toReified

class PetController {
    private fun petApi(): RequestSpecification {
        return RestAssured.given()
            .baseUri("https://petstore.swagger.io/v2")
            .basePath("pet")
            .contentType(ContentType.JSON)
            .header("api_key", "4a0d8b0e-03fe-4ec3-971d-d009fc6a3b2d")
            .log().all()
    }

    fun createPet(targetPet: Pet): Pet? {
        return petApi()
            .body(targetPet.name)
            .post()
            .toReified()
    }

    fun getPetById(id: String): Response {
        return petApi()[id]
    }

    fun deletePetById(id: Long): Response {
        return petApi()
            .delete(id.toString())
    }
}
