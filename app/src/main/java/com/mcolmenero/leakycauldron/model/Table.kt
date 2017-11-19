package com.mcolmenero.leakycauldron.model

import java.io.Serializable

data class Table (var name: String) : Serializable {

    var dish : MutableList<Dish> = arrayListOf()

    // Método para obtener el nombre
    override fun toString() = name


    fun getDishesCount() : Int{
        return dish?.size!!
    }

    fun getDish(position : Int) : Dish{
        return dish[position]
    }
}