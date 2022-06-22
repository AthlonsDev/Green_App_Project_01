package com.example.green_app_project_01

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.green_app_project_01.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.register_layout.*
import java.util.*

class RegisterActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)


        login_button.setOnClickListener {
            registerUserToFirebase()
        }

        link_to_register_text.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    var selectedPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            profile_imageview.setImageBitmap(bitmap)
           // select_photo_button.alpha = 0F

//            val bitmapDrawable = BitmapDrawable(bitmap)
//            photo_circle_image_view.setImageDrawable(bitmapDrawable)

        }
    }




    private fun registerUserToFirebase() {
        val email = editTextEmailAddress.text.toString()
        val password = editTextPassword.text.toString()
//        val proImage = profile_imageview

        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter text in email/password", Toast.LENGTH_SHORT).show()
        }

        Log.d("login", email.toString())
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {

            if(it.isSuccessful) return@addOnCompleteListener

            Log.d("firebase", "Successfully created user with uid: ${it.result?.user?.uid}")
            Toast.makeText(this, "Successfully created user with uid: ${it.result?.user?.uid}", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Log.d("firebase", "Failed to Create user: ${it.message}")
                Toast.makeText(this, "Failed to Create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun uploadImage() {
        if(selectedPhotoUri == null) return
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d("firebase", "succesfully uploaded image: ${it.metadata?.path}")

                ref.downloadUrl.addOnSuccessListener {

                    Log.d("firebase", "File location: ${it.toString()}")



                    saveUsertoDatabase(it.toString())
                }
            }
            .addOnFailureListener{

            }
    }

    private fun saveUsertoDatabase(profileImageUrl: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        val user = User(uid, editTextEmailAddress.text.toString(), profileImageUrl)

        ref.setValue(user)
            .addOnSuccessListener {
                Log.d("firebase", "Saved user to Firebase Database")

            }
    }

}