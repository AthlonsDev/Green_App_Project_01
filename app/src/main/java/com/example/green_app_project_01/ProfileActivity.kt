package com.example.green_app_project_01

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.green_app_project_01.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.profile_layout.*

class ProfileActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.profile_layout)
//        Fetch profile image, userame...
        fetchData()
//        Fetch Data and show it in a recycler view

    }

    private fun fetchData() {
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/$uid")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                showDataInRecView(snapshot)
                val user = snapshot.getValue(User::class.java)
//                Use Picasso to Load Image into View
                username_text_view.text = user?.username
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Firebase", error.message)
            }

        })
    }

    private fun showDataInRecView(snapshot: DataSnapshot) {
        snapshot.children.forEach {
            Log.d("Firebase", it.toString())
//            Use Groupie to Load Data into Recycler View
        }
    }
}