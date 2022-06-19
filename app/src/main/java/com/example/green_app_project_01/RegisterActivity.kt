package com.example.green_app_project_01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.register_layout.*

class RegisterActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)


        register_button.setOnClickListener {
            registerUserToFirebase()
        }

        link_to_login_text.setOnClickListener {
            //TODO: Go to Login Activity
        }
    }
    private fun registerUserToFirebase() {
        val email = editTextEmailAddress.text
        val password = editTextPassword.text
//        val proImage = profile_imageview

        //TODO: Proceed to Register User to Firebase
    }

}