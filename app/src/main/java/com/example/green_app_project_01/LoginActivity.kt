package com.example.green_app_project_01

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.login_layout.*

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_layout)

        login_button.setOnClickListener {
            performLoginFirebase()
        }


    }


    private fun performLoginFirebase() {



    }
}