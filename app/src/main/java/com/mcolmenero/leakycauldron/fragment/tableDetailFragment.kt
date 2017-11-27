package com.mcolmenero.leakycauldron.fragment

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.mcolmenero.leakycauldron.R
import com.mcolmenero.leakycauldron.model.Tables


class tableDetailFragment : Fragment() {

    companion object {
        val ARG_TABLE = "ARG_TABLE"

        fun newInstance(postion: Int): tableDetailFragment {
            val fragment = tableDetailFragment()
            val arguments = Bundle()
            arguments.putInt(ARG_TABLE, postion)
            fragment.arguments = arguments
            return fragment
        }
    }

    //Delegado
    interface OnDetailSelectedListener {
        fun onAddproduct(tablePos: Int)
        fun onCheckBill(tablePos: Int)
    }

    private lateinit var root: View
    private lateinit var list: ListView
    private var tablespos = 0 // Variable de la mesa con la que se está trabajando

    //Objeto del delegado
    private var onDetailSelectedListener: OnDetailSelectedListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_table_detail, container, false)

            //extraigo el parametro
            tablespos = arguments.getInt(ARG_TABLE)

            list = root.findViewById(R.id.table_list_detail)

            //adapter
            list.adapter = ListAdapter(activity, tablespos)


            //evento clicl añadir productos

            root.findViewById<FloatingActionButton?>(R.id.floatButtonAddDish)?.setOnClickListener { v: View ->

                onDetailSelectedListener?.onAddproduct(tablespos)
            }

            root.findViewById<FloatingActionButton?>(R.id.floatButtonBill)?.setOnClickListener { v: View ->

                onDetailSelectedListener?.onCheckBill(tablespos)
            }

        }

        return root
    }


    // Ciclo de vida
    override fun onResume() {
        super.onResume()

        //recargar
        list.adapter = ListAdapter(activity, tablespos)

    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonAttach(context)

    }

    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonAttach(activity)
    }

    override fun onDetach() {
        super.onDetach()
        onDetailSelectedListener = null
    }

    fun commonAttach(listener: Any?) {
        if (listener is OnDetailSelectedListener) {
            onDetailSelectedListener = listener
        }
    }

    // Adaptador para la lista
    internal class ListAdapter(context: Context, val tablepos: Int) : BaseAdapter() {

        private val mInflator: LayoutInflater

        init {
            this.mInflator = LayoutInflater.from(context)
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            //componemos la vista de la celda personalizada

            val view: View?
            val vh: ListRowHolder
            if (convertView == null) {
                view = this.mInflator.inflate(R.layout.cell_table_detail, parent, false) //celda personalizada
                vh = ListRowHolder(view)
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as ListRowHolder
            }

            // Se informan los datos del listado de platos
            formatData(vh, position)

            return view!!

        }

        private fun formatData(vh: ListRowHolder, position: Int) {
            vh.nameDish.text = Tables[tablepos].dishes[position].name
            vh.imageDish.setImageResource(Tables[tablepos].dishes[position].image)
            vh.priceDish.text = Tables[tablepos].dishes[position].price.toString() + " €"
        }

        override fun getItem(position: Int): Any {
            return Tables[tablepos].dishes[position]
        }

        override fun getItemId(position: Int): Long = 0
        override fun getCount() = Tables[tablepos].getDishesCount()


        //Clase Holder
        internal class ListRowHolder(row: View?) {
            val nameDish: TextView
            val imageDish: ImageView
            val priceDish: TextView


            init {

                nameDish = row?.findViewById(R.id.nameTableDish)!!
                imageDish = row?.findViewById(R.id.imageTableDish)!!
                priceDish = row?.findViewById(R.id.priceTableDish)!!

            }
        }

    }

}