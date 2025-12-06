package com.kevin.proyectomovileskch.ui.appointment


import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.kevin.proyectomovileskch.R
import com.kevin.proyectomovileskch.controller.AppointmentController
import com.kevin.proyectomovileskch.data.manager.MemoryDataManager
import com.kevin.proyectomovileskch.data.model.Appointment
import com.kevin.proyectomovileskch.data.model.AppointmentStatus
import java.util.UUID

class AppointmentCrudActivity : AppCompatActivity() {

    private val dataManager = MemoryDataManager()
    private val appointmentController = AppointmentController(dataManager)
    private val currentPatientId = "current-patient"

    private lateinit var etSearchAppointment: EditText
    private lateinit var lvAppointments: ListView
    private lateinit var etDoctorId: EditText
    private lateinit var etDateTime: EditText
    private lateinit var etNotes: EditText
    private lateinit var tvId: TextView
    private lateinit var btnCreate: Button
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var btnClear: Button

    private val appointments = mutableListOf<Appointment>()
    private val displayList = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    private var selectedAppointment: Appointment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment_crud)

        initViews()
        seedAppointmentsIfEmpty()
        loadAppointments()
        setupListeners()
    }

    private fun initViews() {
        etSearchAppointment = findViewById(R.id.etSearchAppointment)
        lvAppointments = findViewById(R.id.lvAppointments)
        etDoctorId = findViewById(R.id.etDoctorId)
        etDateTime = findViewById(R.id.etDateTime)
        etNotes = findViewById(R.id.etNotes)
        tvId = findViewById(R.id.tvAppointmentId)
        btnCreate = findViewById(R.id.btnCreate)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
        btnClear = findViewById(R.id.btnClear)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, displayList)
        lvAppointments.adapter = adapter
    }

    private fun seedAppointmentsIfEmpty() {
        if (appointmentController.listAppointmentsForPatient(currentPatientId).isEmpty()) {
            val now = System.currentTimeMillis()
            appointmentController.createAppointment(
                Appointment(
                    id = UUID.randomUUID().toString(),
                    patientId = currentPatientId,
                    doctorId = "demo-doctor",
                    datetime = now,
                    notes = "First demo appointment",
                    status = AppointmentStatus.PENDING
                )
            )
        }
    }

    private fun loadAppointments(filter: String = "") {
        appointments.clear()
        displayList.clear()

        val all = appointmentController.listAppointmentsForPatient(currentPatientId)
        val lower = filter.lowercase()

        val filtered = if (filter.isEmpty()) all else all.filter {
            (it.notes ?: "").lowercase().contains(lower) ||
                    it.doctorId.lowercase().contains(lower)
        }

        appointments.addAll(filtered)
        displayList.addAll(filtered.map { a ->
            "${a.doctorId} - ${a.notes ?: ""} - ${a.datetime}"
        })

        adapter.notifyDataSetChanged()
    }

    private fun setupListeners() {
        etSearchAppointment.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                loadAppointments(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        lvAppointments.setOnItemClickListener { _, _, index, _ ->
            selectedAppointment = appointments[index]
            tvId.text = selectedAppointment!!.id
            etDoctorId.setText(selectedAppointment!!.doctorId)
            etDateTime.setText(selectedAppointment!!.datetime.toString())
            etNotes.setText(selectedAppointment!!.notes ?: "")
        }

        btnCreate.setOnClickListener { createAppointment() }
        btnUpdate.setOnClickListener { confirmUpdate() }
        btnDelete.setOnClickListener { confirmDelete() }
        btnClear.setOnClickListener { clearForm() }
    }

    private fun createAppointment() {
        if (invalidForm()) return
        val dt = etDateTime.text.toString().trim().toLongOrNull()
        if (dt == null) {
            toast("Invalid timestamp")
            return
        }

        val a = Appointment(
            id = UUID.randomUUID().toString(),
            patientId = currentPatientId,
            doctorId = etDoctorId.text.toString().trim(),
            datetime = dt,
            notes = etNotes.text.toString().trim(),
            status = AppointmentStatus.PENDING
        )

        if (appointmentController.createAppointment(a)) {
            toast("Appointment created successfully")
            clearForm()
            loadAppointments(etSearchAppointment.text.toString())
        }
    }

    private fun updateAppointment() {
        val a = selectedAppointment ?: return
        if (invalidForm()) return

        val dt = etDateTime.text.toString().trim().toLongOrNull()
        if (dt == null) {
            toast("Invalid timestamp")
            return
        }

        a.doctorId = etDoctorId.text.toString().trim()
        a.datetime = dt
        a.notes = etNotes.text.toString().trim()

        if (appointmentController.updateAppointment(a)) {
            toast("Appointment updated successfully")
            clearForm()
            loadAppointments(etSearchAppointment.text.toString())
        }
    }

    private fun deleteAppointment() {
        val a = selectedAppointment ?: return
        if (appointmentController.deleteAppointment(a.id)) {
            toast("Appointment deleted successfully")
            clearForm()
            loadAppointments(etSearchAppointment.text.toString())
        }
    }

    private fun invalidForm(): Boolean {
        val invalid = etDoctorId.text.isBlank() || etDateTime.text.isBlank()
        if (invalid) toast("Please fill doctor id and date")
        return invalid
    }

    private fun confirmUpdate() {
        if (selectedAppointment == null) {
            toast("Please select an appointment first")
            return
        }
        AlertDialog.Builder(this)
            .setTitle("Confirm update")
            .setMessage("Do you really want to update this appointment?")
            .setPositiveButton("Yes") { _, _ -> updateAppointment() }
            .setNegativeButton("No", null)
            .show()
    }

    private fun confirmDelete() {
        if (selectedAppointment == null) {
            toast("Please select an appointment first")
            return
        }
        AlertDialog.Builder(this)
            .setTitle("Confirm delete")
            .setMessage("Do you really want to delete this appointment?")
            .setPositiveButton("Yes") { _, _ -> deleteAppointment() }
            .setNegativeButton("No", null)
            .show()
    }

    private fun clearForm() {
        selectedAppointment = null
        tvId.text = ""
        etDoctorId.text.clear()
        etDateTime.text.clear()
        etNotes.text.clear()
    }

    private fun toast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
