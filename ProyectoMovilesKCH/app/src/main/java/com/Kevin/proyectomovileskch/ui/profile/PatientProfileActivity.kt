package com.kevin.proyectomovileskch.ui.profile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kevin.proyectomovileskch.R

class PatientProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_profile)

        val img = findViewById<ImageView>(R.id.imgProfile)
        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val btnUpdate = findViewById<Button>(R.id.btnUpdateProfile)

        btnUpdate.setOnClickListener {
            // Update in-memory profile or via controllers
            if (etName.text.isNullOrBlank() || etEmail.text.isNullOrBlank()) {
                Toast.makeText(this, getString(R.string.toast_error), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(this, getString(R.string.toast_updated), Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
