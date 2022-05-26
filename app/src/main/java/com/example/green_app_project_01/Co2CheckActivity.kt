package com.example.green_app_project_01

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.co2_check_layout.*

class Co2CheckActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.co2_check_layout)

        button_co2.setOnClickListener {
            calculateCo2()
        }

    }

    private fun calculateCo2() {

//        Rates are for UK
//        1kw/h = 0.281 co2
        val ukRates = 0.281
        val kwh = editText_kwh.text.toString().toInt()

        val co2Ton = kwh * ukRates

        textView_co2_score.text = "You consume an avarage of ${co2Ton.toString()} Tonnes of Co2 every day"
    }

}