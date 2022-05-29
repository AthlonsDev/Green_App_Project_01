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

        button_co2.setOnClickListener {

            calculateCo2()
        }

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

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun calcKilowatts(): Int {
        //        Rates are for UK
//        1kw/h = 0.281 co2
        val ukRates = 0.281
        val kwh = editText_kwh.text.toString().toInt()

        val co2Ton = (kwh * ukRates).roundToInt()
        return co2Ton
    }

    private fun calcFuel(): Int {
//        From Petrol
        val fuelPerLitre = 652+1740

        val inputLitres = fuelCo2_edit.text.toString().toInt()
        val result = fuelPerLitre*inputLitres
        Log.d("fuel", "fuel to co2 = $result")
        return fuelPerLitre * inputLitres
    }

    private fun calculateCo2() {
        val oilRates = 0.650
        val coalRates = 0.9
        val nucRates = 0.005
        val windRates = 0.004
        val solRates = 0.005

        val kwh = editText_kwh.text.toString().toInt()

        val co2Ton = (kwh * oilRates).roundToInt()

        var fuelPerLitre = 652+1740
        if (checkBox_petrol.isChecked) {
            fuelPerLitre = 652+1740

        }
        else if (checkBox_diesel.isChecked) {
            fuelPerLitre = 720+1920

        }
        else if (checkBox_lpg.isChecked) {
            fuelPerLitre = 454+1211

        }

        val inputLitres = fuelCo2_edit.text.toString().toInt()
        val fuel = fuelPerLitre*inputLitres


        val result = co2Ton + fuel
//        textView_co2_score.text = "You consume an avarage of ${kw.toString()} Tonnes of Co2 every day"
        textView_co2_score.text = "You consume an avarage of ${result.toString()} Tonnes of Co2 every day"
    }

}