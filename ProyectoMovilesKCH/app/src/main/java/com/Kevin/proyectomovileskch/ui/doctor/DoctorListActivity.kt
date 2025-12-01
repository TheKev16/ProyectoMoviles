package com.kevin.proyectomovileskch.ui.doctor

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kevin.proyectomovileskch.R
import com.kevin.proyectomovileskch.controller.DoctorController
import com.kevin.proyectomovileskch.data.manager.MemoryDataManager
import com.kevin.proyectomovileskch.data.model.Doctor
import com.kevin.proyectomovileskch.ui.call.CallingActivity
import com.kevin.proyectomovileskch.ui.doctor.adapter.DoctorAdapter

class DoctorListActivity : AppCompatActivity() {

    private val dataManager = MemoryDataManager()
    private val doctorController = DoctorController(dataManager)
    private lateinit var listView: ListView
    private lateinit var adapter: DoctorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_list)
        listView = findViewById(R.id.lvDoctors)

        // Bootstrap sample doctors if empty
        if (doctorController.getAllDoctors().isEmpty()) {
            seedDoctors()
        }

        adapter = DoctorAdapter(this, doctorController.getAllDoctors())
        listView.adapter = adapter

        // Row click -> show contact options
        listView.setOnItemClickListener { parent, view, position, id ->
            val doctor = adapter.getItem(position) ?: return@setOnItemClickListener
            showContactOptions(doctor)
        }

        // More button handled inside adapter via callback (adapter sends click event)
    }

    private fun seedDoctors() {
        doctorController.addDoctor(Doctor(id = "d1", firstName = "Ana", lastName = "Perez", specialty = "Pediatrics"))
        doctorController.addDoctor(Doctor(id = "d2", firstName = "Carlos", lastName = "Lopez", specialty = "Cardiology"))
        doctorController.addDoctor(Doctor(id = "d3", firstName = "María", lastName = "Gonzalez", specialty = "Nutrition"))
    }

    private fun showContactOptions(doctor: Doctor) {
        val options = arrayOf(getString(R.string.contact_send_message), getString(R.string.contact_call))
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.person_name_format, doctor.firstName, doctor.lastName))
            .setItems(options) { _, which ->
                when (which) {
                    0 -> {
                        val i = Intent(this, com.kevin.proyectomovileskch.ui.message.MessageActivity::class.java)
                        i.putExtra("doctorId", doctor.id)
                        startActivity(i)
                    }
                    1 -> {
                        val intent = Intent(this, CallingActivity::class.java)
                        intent.putExtra("CALL_TYPE", "video") // o "audio"
                        startActivity(intent)

                    }
                    2 -> {
                        val i = Intent(this, com.kevin.proyectomovileskch.ui.call.CallingActivity::class.java)
                        i.putExtra("doctorId", doctor.id)
                        startActivity(i)
                    }
                }
            }
            .setNegativeButton(getString(R.string.btn_cancel), null)
            .show()
    }
}