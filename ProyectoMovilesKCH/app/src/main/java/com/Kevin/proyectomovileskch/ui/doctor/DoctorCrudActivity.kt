package com.kevin.proyectomovileskch.ui.doctor

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.kevin.proyectomovileskch.R
import com.kevin.proyectomovileskch.controller.DoctorController
import com.kevin.proyectomovileskch.data.manager.MemoryDataManager
import com.kevin.proyectomovileskch.data.model.Doctor
import java.util.UUID

class DoctorCrudActivity : AppCompatActivity() {

    private val dataManager = MemoryDataManager()
    private val doctorController = DoctorController(dataManager)

    private lateinit var etSearchDoctor: EditText
    private lateinit var lvDoctors: ListView
    private lateinit var etFirst: EditText
    private lateinit var etLast: EditText
    private lateinit var etSpecialty: EditText
    private lateinit var tvId: TextView
    private lateinit var btnCreate: Button
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var btnClear: Button

    private val listDoctors = mutableListOf<Doctor>()
    private val displayList = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    private var doctorSelected: Doctor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_crud)

        initViews()
        loadDoctors()
        setupListeners()
    }

    private fun initViews() {
        etSearchDoctor = findViewById(R.id.etSearchDoctor)
        lvDoctors = findViewById(R.id.lvDoctors)
        etFirst = findViewById(R.id.etFirstName)
        etLast = findViewById(R.id.etLastName)
        etSpecialty = findViewById(R.id.etSpecialty)
        tvId = findViewById(R.id.tvDoctorId)
        btnCreate = findViewById(R.id.btnCreate)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
        btnClear = findViewById(R.id.btnClear)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, displayList)
        lvDoctors.adapter = adapter
    }

    private fun loadDoctors(filter: String = "") {
        val all = doctorController.getAllDoctors()
        listDoctors.clear()
        displayList.clear()

        val lower = filter.lowercase()
        val filtered = if (filter.isEmpty()) all
        else all.filter { doctor ->
            doctor.firstName.lowercase().contains(lower)
                    || doctor.lastName.lowercase().contains(lower)
                    || doctor.specialty.lowercase().contains(lower)
        }

        listDoctors.addAll(filtered)
        displayList.addAll(filtered.map { doctor -> "${doctor.firstName} ${doctor.lastName} - ${doctor.specialty}" })
        adapter.notifyDataSetChanged()
    }

    private fun setupListeners() {
        etSearchDoctor.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                loadDoctors(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        lvDoctors.setOnItemClickListener { _, _, index, _ ->
            doctorSelected = listDoctors[index]
            etFirst.setText(doctorSelected!!.firstName)
            etLast.setText(doctorSelected!!.lastName)
            etSpecialty.setText(doctorSelected!!.specialty)
            tvId.text = doctorSelected!!.id
        }

        btnCreate.setOnClickListener { createDoctor() }
        btnUpdate.setOnClickListener { confirmUpdate() }
        btnDelete.setOnClickListener { confirmDelete() }
        btnClear.setOnClickListener { clearForm() }
    }

    private fun createDoctor() {
        if (invalidForm()) return
        val doctor = Doctor(
            id = UUID.randomUUID().toString(),
            firstName = etFirst.text.toString(),
            lastName = etLast.text.toString(),
            specialty = etSpecialty.text.toString()
        )
        doctorController.addDoctor(doctor)
        clearForm()
        loadDoctors(etSearchDoctor.text.toString())
        toast("Doctor created successfully")
    }

    private fun updateDoctor() {
        if (doctorSelected == null || invalidForm()) return
        doctorSelected!!.apply {
            firstName = etFirst.text.toString()
            lastName = etLast.text.toString()
            specialty = etSpecialty.text.toString()
        }
        doctorController.updateDoctor(doctorSelected!!)
        clearForm()
        loadDoctors(etSearchDoctor.text.toString())
        toast("Doctor updated successfully")
    }

    private fun deleteDoctor() {
        if (doctorSelected == null) return
        doctorController.deleteDoctor(doctorSelected!!.id)
        clearForm()
        loadDoctors(etSearchDoctor.text.toString())
        toast("Doctor deleted successfully")
    }

    private fun invalidForm(): Boolean =
        (etFirst.text.isBlank() || etLast.text.isBlank() || etSpecialty.text.isBlank())
            .also { if (it) toast("Please fill all fields") }

    private fun confirmUpdate() {
        if (doctorSelected == null) return
        AlertDialog.Builder(this)
            .setTitle("Confirm update")
            .setMessage("Do you really want to update this doctor?")
            .setPositiveButton("Yes") { _, _ -> updateDoctor() }
            .setNegativeButton("No", null)
            .show()
    }

    private fun confirmDelete() {
        if (doctorSelected == null) return
        AlertDialog.Builder(this)
            .setTitle("Confirm delete")
            .setMessage("Do you really want to delete this doctor?")
            .setPositiveButton("Yes") { _, _ -> deleteDoctor() }
            .setNegativeButton("No", null)
            .show()
    }

    private fun clearForm() {
        doctorSelected = null
        tvId.text = ""
        etFirst.text.clear()
        etLast.text.clear()
        etSpecialty.text.clear()
    }

    private fun toast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
