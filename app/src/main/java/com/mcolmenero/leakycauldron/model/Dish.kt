package com.mcolmenero.leakycauldron.model

import java.io.Serializable

data class Dish(
        val name: String,
        val image: Int,
        val price: Float,
        val description: String,
        val alg_gluten : Int,
        val alg_crustaceos : Int,
        val alg_huevo : Int,
        val alg_pescado : Int,
        val alg_cacahuete : Int,
        val alg_soja : Int,
        val alg_leche : Int,
        val alg_frutsecos : Int,
        val alg_apio : Int,
        val alg_mostaza : Int,
        val alg_sesamo : Int,
        val alg_sulfitos : Int,
        val alg_altramuces : Int,
        val alg_moluscos : Int,
        val variant: String
    ) : Serializable {

    // Validador del precio
    init {
        if (price !in 0f..100f) {
            throw IllegalArgumentException("Price should be between 0 and 100")

        }

    }
}