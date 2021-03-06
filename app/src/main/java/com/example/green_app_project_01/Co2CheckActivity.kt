package com.example.green_app_project_01

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Debug
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.co2_check_layout.*
import kotlin.math.roundToInt

class Co2CheckActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.co2_check_layout)

        setTitle("Daily Co2")

        manageCheckBoxes()

        //        Run The Calculation Function
        button_co2.setOnClickListener {
            calculateCo2()
        }
    }

//    Close Current Activity Pressing Back Button
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
//  Hide Soft Keyboard Upon Touch Event
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun manageCheckBoxes() {


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

    @SuppressLint("SetTextI18n")
    private fun calculateCo2() {
        var source = 0.0
        when {
            checkBox_oil.isChecked -> {
                source = 0.650
            }
            checkBox_coal.isChecked -> {
                source = 0.900
            }
            checkBox_nuclear.isChecked -> {
                source = 0.005
            }
            checkBox_wind.isChecked -> {
                source = 0.004
            }
            checkBox_solar.isChecked -> {
                source = 0.005
            }
        }

        val kwh = editText_kwh.text.toString().toInt()

        val co2Ton = (kwh * source).roundToInt()

        var fuel = 0
        if (fuelCo2_edit.text.isNotEmpty()) {
            var fuelPerLitre = 0
            when {
                checkBox_petrol.isChecked -> {
                    fuelPerLitre = 652 + 1740

                }
                checkBox_diesel.isChecked -> {
                    fuelPerLitre = 720 + 1920

                }
                checkBox_lpg.isChecked -> {
                    fuelPerLitre = 454 + 1211

                }
            }

            val inputLitres = fuelCo2_edit.text.toString().toInt()
            fuel = fuelPerLitre * inputLitres

        }

        val result = co2Ton + fuel
        textView_co2_score.text = "You consume an average of $result Tonnes of Co2 every day"
    }
}