package com.example.green_app_project_01

import android.os.Bundle
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

    private fun getCarbonFootprint() {
        val electricityScore = convertStrings(textView_Electricty.text.toString())
        val gasScore = convertStrings(textView_Gas.text.toString())
        val fuelScore = convertStrings(textView_Fuel.text.toString())

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