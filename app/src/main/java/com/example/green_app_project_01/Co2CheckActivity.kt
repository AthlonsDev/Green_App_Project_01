package com.example.green_app_project_01

import android.content.Context
import android.os.Bundle
import android.os.Debug
import android.os.PersistableBundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.co2_check_layout.*
import kotlin.math.roundToInt

class Co2CheckActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.co2_check_layout)

//        Run The Calculation Function
        button_co2.setOnClickListener {
            calculateCo2()
        }
//      Choose Source of Fuel
        checkBox_petrol.setOnClickListener {
            checkBox_diesel.isChecked = false
            checkBox_lpg.isChecked = false
        }
        checkBox_diesel.setOnClickListener {
            checkBox_petrol.isChecked = false
            checkBox_lpg.isChecked = false
        }
        checkBox_lpg.setOnClickListener {
            checkBox_diesel.isChecked = false
            checkBox_petrol.isChecked = false
        }

//      Choose Source of Electricity
        checkBox_oil.setOnClickListener {
            checkBox_coal.isChecked = false
            checkBox_nuclear.isChecked = false
            checkBox_wind.isChecked = false
            checkBox_solar.isChecked = false
        }
        checkBox_coal.setOnClickListener {
            checkBox_oil.isChecked = false
            checkBox_nuclear.isChecked = false
            checkBox_wind.isChecked = false
            checkBox_solar.isChecked = false
        }
        checkBox_nuclear.setOnClickListener {
            checkBox_coal.isChecked = false
            checkBox_oil.isChecked = false
            checkBox_wind.isChecked = false
            checkBox_solar.isChecked = false
        }
        checkBox_wind.setOnClickListener {
            checkBox_coal.isChecked = false
            checkBox_nuclear.isChecked = false
            checkBox_oil.isChecked = false
            checkBox_solar.isChecked = false
        }
        checkBox_solar.setOnClickListener {
            checkBox_coal.isChecked = false
            checkBox_nuclear.isChecked = false
            checkBox_wind.isChecked = false
            checkBox_oil.isChecked = false
        }

    }
//  Hide Soft Keyboard Upon Touch Event
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun calculateCo2() {
        var source = 0.0
        if (checkBox_oil.isChecked) {
            source = 0.650
        }
        else if (checkBox_coal.isChecked) {
            source = 0.900
        }
        else if (checkBox_nuclear.isChecked) {
            source = 0.005
        }
        else if (checkBox_wind.isChecked) {
            source = 0.004
        }
        else if (checkBox_solar.isChecked) {
            source = 0.005
        }

        val kwh = editText_kwh.text.toString().toInt()

        val co2Ton = (kwh * source).roundToInt()

        var fuel = 0
        if (fuelCo2_edit.text.isNotEmpty()) {
            var fuelPerLitre = 0
            if (checkBox_petrol.isChecked) {
                fuelPerLitre = 652 + 1740

            } else if (checkBox_diesel.isChecked) {
                fuelPerLitre = 720 + 1920

            } else if (checkBox_lpg.isChecked) {
                fuelPerLitre = 454 + 1211

            }

            val inputLitres = fuelCo2_edit.text.toString().toInt()
            val fuel = fuelPerLitre * inputLitres

        }

        val result = co2Ton + fuel
        textView_co2_score.text = "You consume an avarage of ${result.toString()} Tonnes of Co2 every day"
    }

}