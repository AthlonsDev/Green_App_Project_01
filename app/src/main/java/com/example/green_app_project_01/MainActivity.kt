package com.example.green_app_project_01

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener({
            getCarbonFootprint()
        })


    }

    fun convertStrings(text: String): Int {

        val number = text.toInt()

        return number
    }


    private fun validation(text: String, textView: TextView): Int {
        var textScore = 0
        if(textView_Electricty.text.isNotEmpty()) {
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

        val score = electricityScore*105 + gasScore*105 + fuelScore*113
        textView_score.text = score.toString()
        textView_results.text = processResults(score)
    }

    private fun processResults(results: Int): String {
        var message: String = ""
        val ideal = results >= 6000 && results <= 15999
        val avarage = results >= 16000 && results <= 22000
        val high: Boolean = results >= 22000


        if (ideal) {
            message =
                "Your Carbon Footprint is: " + results.toString() + ", which is Ideal. Good job!"
        } else if (avarage) {
            message =
                "Your Carbon Footprint is: " + results.toString() + ", which is Avarage. Look at some info to get a lower score!"
        } else if (high) {
            message =
                "Your Carbon Footprint is: " + results.toString() + ", which is High. You really need some advice to lower your score!"
        }


        return message
    }


}