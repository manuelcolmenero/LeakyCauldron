package com.mcolmenero.leakycauldron.model

import java.io.Serializable

data class Dish(
        val name: String,
        val image: Int,
        val price: Float,
        val description: String,
     //   val allergen: List<Int>,
        val variant: String
    ) : Serializable {

    // enum de los tipos de alergenos que existen
    enum class AllergenType {
        ALTRAMUCES,
        APIO,
        AZUFRE,
        CACAHUETE,
        CRUSTACEO,
        FRUTOS,
        GLUTEN,
        HUEVO,
        LACTEO,
        MOLUSCOS,
        MOSTAZA,
        PESCADO,
        SESAMO,
        SOJA
    }

    // Validador del precio
    init {
        if (price !in 0f..100f) {
            throw IllegalArgumentException("Price should be between 0 and 100")

        }

    }
}