package com.mcolmenero.leakycauldron.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mcolmenero.leakycauldron.R
import com.mcolmenero.leakycauldron.fragment.tableListFragment
import com.mcolmenero.leakycauldron.model.Table
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), tableListFragment.OnTableSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (table_list_fragment != null) {
            if (fragmentManager.findFragmentById(R.id.table_list_fragment) == null) {
                val fragment = tableListFragment.newInstance()
                fragmentManager.beginTransaction()
                        .replace(R.id.table_list_fragment, fragment)
                        .commit()
            }
        }
    }

    // Cuando se pulsa en una de las mesas se lanza este evento para navegar al detalle
    override fun onTableSelected(table: Table?, position: Int) {
        this.setTitle("Gestionando Mesa  ${position}")

        /* val fragment = tableDetailFragment.newInstance(position)

         // Es tablet
         if (findViewById<View>(R.id.table_detail) != null) {
             fragmentManager.beginTransaction()
                     .replace(R.id.table_detail, fragment)
                     .commit()
         } else {
             fragmentManager.beginTransaction()
                     .replace(R.id.table_list_fragment, fragment)
                     .addToBackStack("")
                     .commit()
         }*/
    }
}
