package com.example.green_app_project_01

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_layout.*
import kotlinx.android.synthetic.main.register_layout.*

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_layout)

        register_button.setOnClickListener {
            performLoginFirebase()
        }

        link_to_register_text.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }


    private fun performLoginFirebase() {
        val email = login_edit_email.text.toString()
        val pass = login_edit_password.text.toString()

        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Please fill out email/pw.", Toast.LENGTH_SHORT).show()
            return
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                val intent = Intent(this, MainActivity::class.java)
                //flags
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to log in: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}