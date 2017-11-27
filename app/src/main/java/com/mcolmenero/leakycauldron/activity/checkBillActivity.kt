package com.mcolmenero.leakycauldron.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mcolmenero.leakycauldron.R
import com.mcolmenero.leakycauldron.model.Tables
import kotlinx.android.synthetic.main.activity_check_bill.*

class checkBillActivity :AppCompatActivity() {

    // Se genera el intercambio de datos
    companion object {
        val ARG_TABLE = "ARG_TABLE"

        fun intent(context: Context, tablePosition: Int): Intent {
            val intent = Intent(context, checkBillActivity::class.java)
            intent.putExtra(ARG_TABLE, tablePosition)
            return intent
        }
    }

    // Variable de la mesa que se ha seleccionado
    private var tablepos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_bill)

        // Se obtiene el parametro
        tablepos = intent.getIntExtra(checkBillActivity.ARG_TABLE, 0)

        //asignamos a items del layout que no son list
        totalCheckBill.text = Tables[tablepos].getTotalPrice().toString() + " €"

    }
}