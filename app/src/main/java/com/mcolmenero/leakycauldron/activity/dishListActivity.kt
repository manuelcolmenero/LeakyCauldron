package com.mcolmenero.leakycauldron.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.mcolmenero.leakycauldron.R
import com.mcolmenero.leakycauldron.model.Dish
import com.mcolmenero.leakycauldron.model.Dishes


class dishListActivity : AppCompatActivity() {

    // Se genera el intercambio de datos
    companion object {
        val ARG_TABLE = "ARG_TABLE"

        fun intent(context: Context, tablePosition: Int): Intent {
            val intent = Intent(context, dishListActivity::class.java)
            intent.putExtra(ARG_TABLE, tablePosition)
            return intent
        }
    }

    private lateinit var list: ListView

    // Variable de la mesa que se ha seleccionado
    private var tablepos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_dish_list)

        // Se obtiene el parametro
        tablepos = intent.getIntExtra(ARG_TABLE, 0)

        list = findViewById(R.id.table_list_dish)

        // Se asigna el adapter
        list.adapter = ListAdapter(this)

        //parametro con la mesa
        tablepos = intent.getIntExtra(ARG_TABLE, 0)


        // Se pulsa sobre un elemento de la lista
        list.setOnItemClickListener { parent, view, position, id ->

            //lanzamos el intent
            val intent = dishDetailActivity.intent(this, tablepos,position)
            startActivity(intent)
        }


    }

    // ***********************
    // Adaptador para la lista
    // ***********************
    internal class ListAdapter(context: Context) : BaseAdapter() {

        private val mInflator: LayoutInflater

        init {
            this.mInflator = LayoutInflater.from(context)
        }

        @SuppressLint("SetTextI18n")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            //componemos la vista de la celda personalizada

            val view: View?
            val vh: ListRowHolder


            val dishItem = Dishes.getDish(position)

            if (convertView == null) {
                view = this.mInflator.inflate(R.layout.cell_dish_list, parent, false) //celda personalizada
                vh = ListRowHolder(view)
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as ListRowHolder
            }

            // Se asignan los valores a la celda
            formatCell(vh, dishItem)

            return view!!

        }

        @SuppressLint("SetTextI18n")
        private fun formatCell(vh: ListRowHolder, dishItem: Dish) {
            vh.nameDish.text = dishItem.name
            vh.imageDish.setImageResource(dishItem.image)
            vh.priceDish.text = dishItem.price.toString() + " €"

            putImage(vh.alergen1, 1, dishItem.alg_gluten)
            putImage(vh.alergen2, 2, dishItem.alg_crustaceos)
            putImage(vh.alergen3, 3, dishItem.alg_huevo)
            putImage(vh.alergen4, 4, dishItem.alg_pescado)
            putImage(vh.alergen5, 5, dishItem.alg_cacahuete)
            putImage(vh.alergen6, 6, dishItem.alg_soja)
            putImage(vh.alergen7, 7, dishItem.alg_leche)
            putImage(vh.alergen8, 8, dishItem.alg_frutsecos)
            putImage(vh.alergen9, 9, dishItem.alg_apio)
            putImage(vh.alergen10, 10, dishItem.alg_mostaza)
            putImage(vh.alergen11, 11, dishItem.alg_sesamo)
            putImage(vh.alergen12, 12, dishItem.alg_sulfitos)
            putImage(vh.alergen13, 13, dishItem.alg_altramuces)
            putImage(vh.alergen14, 14, dishItem.alg_moluscos)
        }

        private fun putImage(alergen: ImageView, numAlergen: Int, valueDish: Int) {

            // Se pone visible o translucido para ponerlo visible o no
            alergen.alpha = 1f
            if (valueDish == 0) {
                alergen.alpha = 0.3f
            }
            when (numAlergen) {
                1 -> alergen.setImageResource(R.drawable.alergenogluten)
                2 -> alergen.setImageResource(R.drawable.alergenocrustaceo)
                3 -> alergen.setImageResource(R.drawable.alergenohuevos)
                4 -> alergen.setImageResource(R.drawable.alergenopescado)
                5 -> alergen.setImageResource(R.drawable.alergenocacahute)
                6 -> alergen.setImageResource(R.drawable.alergenosoja)
                7 -> alergen.setImageResource(R.drawable.alergenolacteo)
                8 -> alergen.setImageResource(R.drawable.alergenofrutos)
                9 -> alergen.setImageResource(R.drawable.alergenoapio)
                10 -> alergen.setImageResource(R.drawable.alergenomostaza)
                11 -> alergen.setImageResource(R.drawable.alergenosesamo)
                12 -> alergen.setImageResource(R.drawable.alergenoazufre)
                13 -> alergen.setImageResource(R.drawable.alergenoaltramuces)
                14 -> alergen.setImageResource(R.drawable.alergenomolusco)
            }
        }

        override fun getItem(position: Int): Any {
            return Dishes.getDish(position)
        }

        override fun getItemId(position: Int): Long = 0
        override fun getCount() = Dishes.count

        // -----------------------
        // Clase Holder
        // -----------------------
        internal class ListRowHolder(row: View?) {
            val nameDish: TextView
            val imageDish: ImageView
            val priceDish: TextView
            val alergen1: ImageView
            val alergen2: ImageView
            val alergen3: ImageView
            val alergen4: ImageView
            val alergen5: ImageView
            val alergen6: ImageView
            val alergen7: ImageView
            val alergen8: ImageView
            val alergen9: ImageView
            val alergen10: ImageView
            val alergen11: ImageView
            val alergen12: ImageView
            val alergen13: ImageView
            val alergen14: ImageView


            init {

                nameDish = row?.findViewById(R.id.nameListDish)!!
                imageDish = row.findViewById(R.id.imageListDish)!!
                priceDish = row.findViewById(R.id.priceListDish)

                alergen1 = row.findViewById(R.id.alergen1)
                alergen2 = row.findViewById(R.id.alergen2)
                alergen3 = row.findViewById(R.id.alergen3)
                alergen4 = row.findViewById(R.id.alergen4)
                alergen5 = row.findViewById(R.id.alergen5)
                alergen6 = row.findViewById(R.id.alergen6)
                alergen7 = row.findViewById(R.id.alergen7)
                alergen8 = row.findViewById(R.id.alergen8)
                alergen9 = row.findViewById(R.id.alergen9)
                alergen10 = row.findViewById(R.id.alergen10)
                alergen11 = row.findViewById(R.id.alergen11)
                alergen12 = row.findViewById(R.id.alergen12)
                alergen13 = row.findViewById(R.id.alergen13)
                alergen14 = row.findViewById(R.id.alergen14)


            }
        }

    }
}