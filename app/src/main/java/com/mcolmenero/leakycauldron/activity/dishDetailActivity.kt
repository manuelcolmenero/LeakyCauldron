package com.mcolmenero.leakycauldron.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mcolmenero.leakycauldron.R
import com.mcolmenero.leakycauldron.model.Dish
import com.mcolmenero.leakycauldron.model.Dishes
import com.mcolmenero.leakycauldron.model.Tables
import kotlinx.android.synthetic.main.activity_dish_detail.*


class dishDetailActivity : AppCompatActivity() {


    // Se genera el intercambio de datos
    companion object {
        val ARG_TABLE = "ARG_TABLE"
        val ARG_DISH = "ARG_DISH"

        fun intent(context: Context, tablePosition: Int, dishPosition: Int): Intent {
            val intent = Intent(context, dishDetailActivity::class.java)
            intent.putExtra(ARG_TABLE, tablePosition)
            intent.putExtra(ARG_DISH, dishPosition)

            return intent
        }
    }

    // Variable de la mesa que se ha seleccionado
    private var tablepos = 0

    // Variable del plato que se ha seleccionado
    private var dishpos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_detail)

        // Se obtienen los parametros recibidos
        tablepos = intent.getIntExtra(ARG_TABLE, 0)
        dishpos = intent.getIntExtra(ARG_DISH, 0)

        // Se informan los campos de la pantalla
        formatDetail()

        // Se pulsa en el boton de añadir platos

        addDishDetail.setOnClickListener {
            // Se añade el menu a la mesa
            val table = Tables[tablepos]
            val dishToAdd = Dishes.getDish(dishpos)
            val variantClient = variantsDishDetail.text.toString()

            table.dishes.add(0,
                    Dish(dishToAdd.name,
                            dishToAdd.image,
                            dishToAdd.price,
                            dishToAdd.description,
                            dishToAdd.alg_gluten,
                            dishToAdd.alg_crustaceos,
                            dishToAdd.alg_huevo,
                            dishToAdd.alg_pescado ,
                            dishToAdd.alg_cacahuete,
                            dishToAdd.alg_soja,
                            dishToAdd.alg_leche ,
                            dishToAdd.alg_frutsecos,
                            dishToAdd.alg_apio,
                            dishToAdd.alg_mostaza,
                            dishToAdd.alg_sesamo,
                            dishToAdd.alg_sulfitos,
                            dishToAdd.alg_altramuces,
                            dishToAdd.alg_moluscos,
                            variantClient))
            // finalizamos actividad
            finish()
        }

    }

    private fun formatDetail() {
        nameDetailDish.text = Dishes.getDish(dishpos).name
        imageDetailDish.setImageResource(Dishes.getDish(dishpos).image)
        priceDishDetail.text = Dishes.getDish(dishpos).price.toString() + " €"
        descriptionDetailDish.text = Dishes.getDish(dishpos).description
    }

}