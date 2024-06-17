package io.testomat

class Api {

    fun ololo(id: Long?, name: String = "default", type: String = "test default") {
        println("id = $id, name = $name, type = $type")
    }
}

fun main() {
    Api().ololo(id = 1)
    Api().ololo(id = 1, name = "test popo")
    Api().ololo(id = 1, name = "test popo", type = "test iuiu")
    Api().ololo(id = 1, type = "test iuiu")

}

data class Pet(var id: Long, val name: String?, var type: String?)
