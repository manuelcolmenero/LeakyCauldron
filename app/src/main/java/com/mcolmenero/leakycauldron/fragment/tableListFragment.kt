package com.mcolmenero.leakycauldron.fragment

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.mcolmenero.leakycauldron.R
import com.mcolmenero.leakycauldron.model.Table
import com.mcolmenero.leakycauldron.model.Tables

class tableListFragment : Fragment() {

    companion object {

        fun newInstance(): tableListFragment {
            return tableListFragment()
        }
    }

    //Delegado
    interface OnTableSelectedListener {
        fun onTableSelected(table: Table?, position: Int)
    }

    // Declaración de variables
    private lateinit var root: View
    private lateinit var list: ListView

    // Declaración del objeto delegado
    private var onTableSelectedListener: OnTableSelectedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_table_list, container, false)
            list = root.findViewById(R.id.table_list)

            //adapter
            list.adapter = ListAdapter(activity)

            // Nos enteramos de que se ha pulsado un elemento de la lista así:
            list.setOnItemClickListener { parent, view, position, id ->
                // Aviso al listener
                onTableSelectedListener?.onTableSelected(Tables[position], position)


            }

        }
        return root
    }

    // Ciclo de vida
    override fun onResume() {
        super.onResume()

        //aqui es el siti, donde se pone, por ejemplo, la recargha de una lista
        val adapter = ListAdapter(activity) //asignamos el adaptador

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
        onTableSelectedListener = null
    }

    private fun commonAttach(listener: Any?) {
        if (listener is OnTableSelectedListener) {
            onTableSelectedListener = listener
        }
    }

    // Adaptador para la lista
    internal class ListAdapter(context: Context) : BaseAdapter() {

        private val mInflator: LayoutInflater

        init {
            this.mInflator = LayoutInflater.from(context)
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            //componemos la vista de la celda personalizada

            val view: View?
            val vh: ListRowHolder
            if (convertView == null) {
                view = this.mInflator.inflate(R.layout.cell_table_list, parent, false) //celda personalizada
                vh = ListRowHolder(view)
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as ListRowHolder
            }

            //asignamos a la vista los calores
            vh.titleCell.text = Tables[position].name

            return view!!

        }

        override fun getItem(position: Int): Any {
            return Tables[position]
        }

        override fun getItemId(position: Int): Long = 0
        override fun getCount() = Tables.count


        //Clase Holder
        internal class ListRowHolder(row: View?) {
            public val titleCell: TextView


            init {

                titleCell = row?.findViewById(R.id.txtTitleCell)!!

            }
        }

    }

}
