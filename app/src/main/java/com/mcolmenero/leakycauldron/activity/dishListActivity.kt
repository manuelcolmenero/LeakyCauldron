package com.mcolmenero.leakycauldron.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mcolmenero.leakycauldron.R
import com.mcolmenero.leakycauldron.model.Dish
import com.mcolmenero.leakycauldron.model.Tables


class dishListActivity : AppCompatActivity() {

    companion object {
        val ARG_TABLE = "ARG_TABLE"

        fun intent(context: Context, tablePosition: Int): Intent {
            val intent = Intent(context, dishListActivity::class.java)
            intent.putExtra(ARG_TABLE, tablePosition)
            return intent
        }
    }

    private var tablepos = 0 // Variable de la mesa con la que se está trabajando

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_list)

        //extraigo el parametro
        tablepos = intent.getIntExtra (ARG_TABLE, 0)
        //capturamos la mesa
        /*
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        */


        //demo
        Tables[tablepos].dish.add(0, Dish("Cerveza", R.drawable.cerveza, 10.45f, "Cerveza de mantequilla", ""))


    }
}