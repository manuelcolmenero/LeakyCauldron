package com.mcolmenero.leakycauldron.model

import java.io.Serializable

data class Table (var name: String) : Serializable {

    var dishes : MutableList<Dish> = arrayListOf()

    // Método para obtener el nombre
    override fun toString() = name


    fun getDishesCount() : Int{
        return dishes?.size!!
    }

    fun getTotalPrice() : Float {
        var  totalPrice = 0.0f;

        if (dishes.count() != 0) {

            for (index in 0 until dishes.size) {
                totalPrice += dishes[index].price
            }

        }

        return totalPrice
    }
}