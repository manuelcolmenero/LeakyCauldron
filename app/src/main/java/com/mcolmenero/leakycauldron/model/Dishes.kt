package com.mcolmenero.leakycauldron.model

import com.mcolmenero.leakycauldron.R


object Dishes {

    // Se crea e inicializa la lista de platos
    val dishes: MutableList<Dish> = arrayListOf()

    // Propiedad para saber el número de objetos que posee
    val count
        get() = dishes?.size

    // Metodos para poder trabajar con Dishes
    fun getDish(position: Int) = dishes?.get(position)

    fun addDish(name: String,
                image: Int,
                price: Float,
                description: String,
                alg_gluten: Int,
                alg_crustaceos: Int,
                alg_huevo: Int,
                alg_pescado: Int,
                alg_cacahuete: Int,
                alg_soja: Int,
                alg_leche: Int,
                alg_frutsecos: Int,
                alg_apio: Int,
                alg_mostaza: Int,
                alg_sesamo: Int,
                alg_sulfitos: Int,
                alg_altramuces: Int,
                alg_moluscos: Int,
                variant: String) {

        dishes.add(Dish(name,
                image,
                price,
                description,
                alg_gluten,
                alg_crustaceos,
                alg_huevo,
                alg_pescado,
                alg_cacahuete,
                alg_soja,
                alg_leche,
                alg_frutsecos,
                alg_apio,
                alg_mostaza,
                alg_sesamo,
                alg_sulfitos,
                alg_altramuces,
                alg_moluscos,
                variant))
    }
}