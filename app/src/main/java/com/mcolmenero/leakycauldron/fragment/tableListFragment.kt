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
import android.widget.ViewSwitcher
import com.mcolmenero.leakycauldron.R
import com.mcolmenero.leakycauldron.model.Dishes.addDish
import com.mcolmenero.leakycauldron.model.Dishes.dishes
import com.mcolmenero.leakycauldron.model.Table
import com.mcolmenero.leakycauldron.model.Tables
import com.mcolmenero.leakycauldron.model.URL_DISH_LIST
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.json.JSONObject
import java.net.URL
import java.util.*

class tableListFragment : Fragment() {

    // Se crea un enumerado para las vistas del fragment (loading o lista de mesas)
    enum class VIEW_INDEX(val index: Int) {
        LOADING(0),
        TABLES(1)
    }

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
    lateinit var viewSwitcher: ViewSwitcher

    // Declaración del objeto delegado
    private var onTableSelectedListener: OnTableSelectedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_table_list, container, false)
            list = root.findViewById(R.id.table_list)

            //Switch View del fragment
            viewSwitcher = root.findViewById(R.id.viewSwitcher)
            viewSwitcher.setInAnimation(activity, android.R.anim.fade_in)

            // Nos enteramos de que se ha pulsado un elemento de la lista así:
            list.setOnItemClickListener { parent, view, position, id ->
                // Aviso al listener
                onTableSelectedListener?.onTableSelected(Tables[position], position)


            }

            // Se descarga el listado de platos que conforma el menú
            downloadDishes()

        }
        return root
    }

    // ***********************
    // Función para la descarga del listado de platos que conforma el menú
    // ***********************
    private fun downloadDishes() {

        // Se pone la barra de descarga
        viewSwitcher.displayedChild = VIEW_INDEX.LOADING.index

        // En caso de existir datos se carga la lista de mesas
        if (dishes.count() != 0) {

            // Se carga la lista de mesas
            viewSwitcher.displayedChild = VIEW_INDEX.TABLES.index //Se cambia vista al principal que es la lista de mesas

            // Se lanza el listado
            list.adapter = ListAdapter(activity)
            return

        }
        // En caso de no existir datos se descargan del servidor
        else {
            // Se lanzan los siguientes pasos en modo asincrono
            async(UI) {
                // Se ejecuta la descarga de datos desde URL
                val data: Deferred<Unit> = bg {
                    downloadData()
                }

                // Se entra en hilo secundario
                data.await()

                // Se carga la lista de mesas
                viewSwitcher.displayedChild = VIEW_INDEX.TABLES.index //Se cambia vista al principal que es la lista de mesas

                // Se asigna el adapter
                list.adapter = ListAdapter(activity) //asignamos el adapter a la lista

                // Se carga el primer registro en caso de estar en el modo tablet
                if (root.findViewById<View>(R.id.table_detail) != null) {
                    // Se verifica si ya existe el fragment
                    if (fragmentManager.findFragmentById(R.id.table_detail) == null) {
                        val fragment = tableDetailFragment.newInstance(0)
                        fragmentManager.beginTransaction().add(R.id.table_detail, fragment)
                                .addToBackStack("")
                                .commit()
                    }
                }

            }

        }
    }


    // Función de descarga de datos desde URL para poder utilizarlo en la aplicación
    private fun downloadData(): Unit {
        try {
            // Se descarga la información de la URL
            val url = URL(URL_DISH_LIST)
            val jsonString = Scanner(url.openStream(), "UTF-8").useDelimiter("\\A").next()

            // Se analizan los que se han descargado
            val jsonRoot = JSONObject(jsonString)

            // Se obtiene el array
            val data = jsonRoot.getJSONArray("dishes")


            //recorrmeos cada plato.
            for (dish in 0..data.length() - 1) {

                val dataMenu = data.getJSONObject(dish)

                val name = dataMenu.getString("name")
                val image = dataMenu.getInt("image")
                val price = dataMenu.getDouble("price").toFloat()
                val description = dataMenu.getString("description")
                val alg_gluten = dataMenu.getInt("alg_gluten")
                val alg_crustaceos = dataMenu.getInt("alg_crustaceos")
                val alg_huevo = dataMenu.getInt("alg_huevo")
                val alg_pescado = dataMenu.getInt("alg_pescado")
                val alg_cacahuete = dataMenu.getInt("alg_cacahuete")
                val alg_soja = dataMenu.getInt("alg_soja")
                val alg_leche = dataMenu.getInt("alg_leche")
                val alg_frutsecos = dataMenu.getInt("alg_frutsecos")
                val alg_apio = dataMenu.getInt("alg_apio")
                val alg_mostaza = dataMenu.getInt("alg_mostaza")
                val alg_sesamo = dataMenu.getInt("alg_sesamo")
                val alg_sulfitos = dataMenu.getInt("alg_sulfitos")
                val alg_altramuces = dataMenu.getInt("alg_altramuces")
                val alg_moluscos = dataMenu.getInt("alg_moluscos")
                val variant = dataMenu.getString("variant")

                val iconResource = when (image) {
                    1 -> R.drawable.cerveza
                    2 -> R.drawable.pollo
                    3 -> R.drawable.salmon
                    4 -> R.drawable.hamburguesa
                    else -> R.drawable.cerveza
                }

                //añado al singleton
                addDish(name,
                        iconResource,
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
                        variant)

            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    // ***********************
    // Ciclo de vida
    // ***********************
    override fun onResume() {
        super.onResume()

        // Se recarga la lista
        list.adapter = ListAdapter(activity)

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

    // ***********************
    // Adaptador para la lista
    // ***********************
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

        // -----------------------
        // Clase Holder
        // -----------------------
        internal class ListRowHolder(row: View?) {
            public val titleCell: TextView


            init {

                titleCell = row?.findViewById(R.id.txtTitleCell)!!

            }
        }

    }

}
