package io.testomat

import io.testomat.common.toReified

class MapAndListExp {

    fun mapAndList() {
        val map = mapOf(1 to "one", 2 to "two", 3 to "three")
        val list = listOf(1, 2, 3, 4, 5)
        println(list)


        val mutableList = mutableListOf(1, 2, 3, 4, 5)

        list.map { it * 2 }.forEach { println(it) }

        mutableList.apply { add(6) }

        println(map)
        println(list)

        PetController().apply {
            createPet(Pet(1, "dog", "mammal"))
            getPetById("1").toReified()
        }

    }

}


fun main() {
    MapAndListExp().mapAndList()
}
