package com.kevin.proyectomovileskch.ui.appointment

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kevin.proyectomovileskch.R

class AddAppointmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_appointment)

        val etDoctor = findViewById<EditText>(R.id.etDoctorName)
        val etDate = findViewById<EditText>(R.id.etDate)
        val etReason = findViewById<EditText>(R.id.etReason)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            // For prototype: simple validation and toast
            if (etDoctor.text.isNullOrBlank() || etDate.text.isNullOrBlank() || etReason.text.isNullOrBlank()) {
                Toast.makeText(this, getString(R.string.toast_error), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(this, getString(R.string.toast_saved), Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
