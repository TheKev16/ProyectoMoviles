package com.kevin.proyectomovileskch.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kevin.proyectomovileskch.ui.appointment.AppointmentCrudActivity
import com.kevin.proyectomovileskch.R
import com.kevin.proyectomovileskch.ui.AddImagesActivity
import com.kevin.proyectomovileskch.ui.CallHistoryActivity
import com.kevin.proyectomovileskch.ui.ChatHistoryActivity
import com.kevin.proyectomovileskch.ui.HealthTipsActivity
import com.kevin.proyectomovileskch.ui.LoginActivity
import com.kevin.proyectomovileskch.ui.MonitorActivity
import com.kevin.proyectomovileskch.ui.appointment.AppointmentListActivity
import com.kevin.proyectomovileskch.ui.call.CallingActivity
import com.kevin.proyectomovileskch.ui.doctor.DoctorCrudActivity
import com.kevin.proyectomovileskch.ui.doctor.DoctorListActivity
import com.kevin.proyectomovileskch.ui.patient.PatientCrudActivity
import com.kevin.proyectomovileskch.ui.profile.PatientProfileActivity

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
        val btnBottomSearch = findViewById<ImageButton>(R.id.btnBottomSearch)
        val btnBottomHome = findViewById<ImageButton>(R.id.btnBottomHome)
        val btnBottomInfo = findViewById<ImageButton>(R.id.btnBottomInfo)


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
            startActivity(Intent(this, CallHistoryActivity::class.java))
        }

        btnAppointments.setOnClickListener {
            startActivity(Intent(this, AppointmentCrudActivity::class.java))
        }

        btnBottomSearch.setOnClickListener {
            startActivity(Intent(this, DoctorCrudActivity::class.java))
        }

        btnBottomHome.setOnClickListener {
            Toast.makeText(this, "You are in Home", Toast.LENGTH_SHORT).show()
        }

        btnBottomInfo.setOnClickListener {
            startActivity(Intent(this, PatientCrudActivity::class.java))
        }

    }
}