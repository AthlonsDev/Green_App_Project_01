package com.example.green_app_project_01

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            getCarbonFootprint()
        }

        setTitle("Welcome to Green_App")

//        **App Workflow**
//        Check Monthly Score TODO: find highest value and point it with maybe advices on how to bring it down
//        Check co2 Daily Usage
//        TODO:Upload Data To Database
//        TODO:Show Personal Scores Connects to Social Apps
//        TODO:Gather Relevant News and Show them Using Recycler View
//        TODO:UI Design
//        TODO:Add Achievements and/or badges

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }


    private fun validation(text: String, textView: TextView): Int {
        var textScore = 0
        if(text.isNotEmpty()) {
            textScore = text.toInt()
            if(textView.id == textView_Electricty.id) {
                imageView_el_warning.alpha = 0F
            }
            if(textView.id == textView_Electricty.id) {
                imageView_gs_warning.alpha = 0F
            }
            if(textView.id == textView_Electricty.id) {
                imageView_fl_warning.alpha = 0F
            }
        } else {
            Log.d("validation", "${textView.id} Field missing")
            if(textView.id == textView_Electricty.id) {
                imageView_el_warning.alpha = 1F
            }
            if(textView.id == textView_Electricty.id) {
                imageView_gs_warning.alpha = 1F
            }
            if(textView.id == textView_Electricty.id) {
                imageView_fl_warning.alpha = 1F
            }
        }
        return textScore
    }

    private fun getCarbonFootprint() {
        val electricityScore = validation(textView_Electricty.text.toString(), textView_Electricty)
        val gasScore = validation(textView_Gas.text.toString(), textView_Gas)
        val fuelScore = validation(textView_Fuel.text.toString(), textView_Fuel)
        val flightNumb = validation(textView_flights.text.toString(), textView_flights)

        var flightResult = flightNumb*1100
        if (switch_flight.isChecked) {
            flightResult = flightNumb*4400
        }
        var paperRec = 184
        var metalRec = 166
        if (switch_paper.isChecked)
            paperRec = 0
        if (switch_metal.isChecked)
            metalRec = 0

//        val newScore = avg kw/h + avg gas/h + avg km + flights + recycle
//        val score = (electricityScore*0.74 + gasScore*454 + fuelScore*652).toInt()

//        Find Highest Value
        val highestValue = findHighest(electricityScore, gasScore, fuelScore)

        val score = electricityScore*105 + gasScore*105 + fuelScore*113 + flightResult + paperRec + metalRec
        textView_score.text = score.toString()
        textView_results.text = processResults(score, highestValue)
    }

    private fun processResults(results: Int, value: String): String {
        var message = ""
        val ideal = results in 6000..15999
        val average = results in 16000..22000
        val high: Boolean = results >= 22000


        when {
            ideal -> {
                message =
                    "Your Carbon Footprint is: $results, which is Ideal. Good job!"
            }
            average -> {
                message =
                    "Your Carbon Footprint is: $results, which is Average. Look at some info to get a lower score! The Highest Value Appears to be $value"
            }
            high -> {
                message =
                    "Your Carbon Footprint is: $results, which is High. You really need some advice to lower your score!  The Highest Value Appears to be $value"
            }
        }

        return message

    }

    private fun findHighest(e: Int, g: Int, f: Int): String {
    var result = ""
        if (e > g && e > f) {
//            Electricity is Highest Value
            result = "electricity"
        }
        if (g > e && g > f) {
//            Gas is Highest Value
            result = "gas"
        }
        if (f > e && f > g) {
//            Fuel is Highest Value
            result = "fuel"
        }
    return result
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_bar, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_check_co2 -> {
                val intent = Intent(this, Co2CheckActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}