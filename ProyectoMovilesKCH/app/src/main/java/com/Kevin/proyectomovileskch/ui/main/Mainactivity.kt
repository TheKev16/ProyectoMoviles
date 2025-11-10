package com.kevin.proyectomovileskch.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.kevin.proyectomovileskch.R
import com.kevin.proyectomovileskch.ui.LoginActivity
import com.kevin.proyectomovileskch.ui.appointment.AppointmentListActivity
import com.kevin.proyectomovileskch.ui.doctor.DoctorListActivity
import com.kevin.proyectomovileskch.ui.profile.PatientProfileActivity
import com.kevin.proyectomovileskch.ui.AddImagesActivity
import com.kevin.proyectomovileskch.ui.MonitorActivity
import com.kevin.proyectomovileskch.ui.HealthTipsActivity
import com.kevin.proyectomovileskch.ui.ChatHistoryActivity
import com.kevin.proyectomovileskch.ui.call.CallingActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDoctors = findViewById<LinearLayout>(R.id.btnDoctors)
        val btnAppointments = findViewById<LinearLayout>(R.id.btnAppointments)
        val btnProfile = findViewById<LinearLayout>(R.id.btnProfile)
        val btnLoginIcon = findViewById<ImageButton>(R.id.btnLoginIcon)
        val btnAddImages = findViewById<LinearLayout>(R.id.btnAddImages)
        val btnMonitorActivity = findViewById<LinearLayout>(R.id.btnMonitorActivity)
        val btnHealthTips = findViewById<LinearLayout>(R.id.btnHealthTips)
        val btnChatHistory = findViewById<LinearLayout>(R.id.btnChatHistory)
        val btnCallHistory = findViewById<LinearLayout>(R.id.btnCallHistory)


        btnDoctors.setOnClickListener {
            startActivity(Intent(this, DoctorListActivity::class.java))
        }

        btnAppointments.setOnClickListener {
            startActivity(Intent(this, AppointmentListActivity::class.java))
        }

        btnProfile.setOnClickListener {
            startActivity(Intent(this, PatientProfileActivity::class.java))
        }

        btnLoginIcon.setOnClickListener {
            // For prototype: go to login (or profile)
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btnAddImages.setOnClickListener {
            startActivity(Intent(this, AddImagesActivity::class.java))
        }

        btnMonitorActivity.setOnClickListener {
            startActivity(Intent(this, MonitorActivity::class.java))
        }

        btnHealthTips.setOnClickListener {
            startActivity(Intent(this, HealthTipsActivity::class.java))
        }

        btnChatHistory.setOnClickListener {
            startActivity(Intent(this, ChatHistoryActivity::class.java))
        }

        btnCallHistory.setOnClickListener {
            startActivity(Intent(this, CallingActivity::class.java))
        }
    }
}
