package com.kevin.proyectomovileskch.ui.appointment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.kevin.proyectomovileskch.R

class AppointmentListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment_list)

        val btnAdd = findViewById<Button>(R.id.btnAddAppointment)
        val lv = findViewById<ListView>(R.id.lvAppointments)

        btnAdd.setOnClickListener {
            startActivity(Intent(this, AddAppointmentActivity::class.java))
        }

        // For prototype, list is empty; implement adapter and DataManager calls to show appointments
    }
}
