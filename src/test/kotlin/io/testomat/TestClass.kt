package io.testomat

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class TestClass {

    @Test
    fun `creaete test suite test`() {
        targetStrin = "sadfsadfsda"

        val pet = Pet(id = 0, name = "test", type = "test")

        val createPet = PetController().createPet(pet)
        Assertions.assertThat(createPet?.name).isEqualTo(200)
    }

    companion object {
        lateinit var targetStrin: String

        @JvmStatic
        @BeforeAll
        fun setUp(): Unit {
            targetStrin = "sdf"
        }

    }

}
