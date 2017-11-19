package com.mcolmenero.leakycauldron.model

import java.io.Serializable

object Tables : Serializable{
    private var tables: List<Table> = listOf(
            Table("Mesa 1"),
            Table("Mesa 2"),
            Table("Mesa 3"),
            Table("Mesa 4"),
            Table("Mesa 5"),
            Table("Mesa 6"),
            Table("Mesa 7"),
            Table("Mesa 8"),
            Table("Mesa 9"),
            Table("Mesa 10"),
            Table("Mesa 11"),
            Table("Mesa 12")
    )

    val count
        get() = tables.size

    operator fun  get(index: Int) = tables[index]

    fun toArray() = tables.toTypedArray()
}