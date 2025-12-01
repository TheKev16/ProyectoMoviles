package com.kevin.proyectomovileskch.ui.patient

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kevin.proyectomovileskch.R
import com.kevin.proyectomovileskch.controller.UserController
import com.kevin.proyectomovileskch.data.manager.MemoryDataManager
import com.kevin.proyectomovileskch.data.model.Person
import java.util.UUID

class PatientCrudActivity : AppCompatActivity() {

    private val dataManager = MemoryDataManager()
    private val userController = UserController(dataManager)

    private lateinit var etSearchPatient: EditText
    private lateinit var lvPatients: ListView
    private lateinit var etFirst: EditText
    private lateinit var etLast: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var tvId: TextView
    private lateinit var btnCreate: Button
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var btnClear: Button

    private val patients = mutableListOf<Person>()
    private val displayList = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    private var selectedPatient: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_crud)

        initViews()
        seedPatientsIfEmpty()
        loadPatients()
        setupListeners()
    }

    private fun initViews() {
        etSearchPatient = findViewById(R.id.etSearchPatient)
        lvPatients = findViewById(R.id.lvPatients)
        etFirst = findViewById(R.id.etFirstName)
        etLast = findViewById(R.id.etLastName)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)
        tvId = findViewById(R.id.tvPatientId)
        btnCreate = findViewById(R.id.btnCreate)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
        btnClear = findViewById(R.id.btnClear)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, displayList)
        lvPatients.adapter = adapter
    }

    private fun seedPatientsIfEmpty() {
        if (userController.listPersons().isEmpty()) {
            userController.registerPerson(
                Person(
                    id = UUID.randomUUID().toString(),
                    firstName = "John",
                    lastName = "Doe",
                    email = "john@example.com",
                    phone = "12345678"
                )
            )
            userController.registerPerson(
                Person(
                    id = UUID.randomUUID().toString(),
                    firstName = "Mary",
                    lastName = "Smith",
                    email = "mary@example.com",
                    phone = "87654321"
                )
            )
        }
    }

    private fun loadPatients(filter: String = "") {
        patients.clear()
        displayList.clear()

        val all = userController.listPersons()
        val lower = filter.lowercase()

        val filtered = if (filter.isEmpty()) all else all.filter {
            it.firstName.lowercase().contains(lower)
                    || it.lastName.lowercase().contains(lower)
                    || (it.email ?: "").lowercase().contains(lower)
                    || (it.phone ?: "").lowercase().contains(lower)
        }

        patients.addAll(filtered)
        displayList.addAll(
            filtered.map { p ->
                "${p.firstName} ${p.lastName} - ${p.email ?: ""}"
            }
        )
        adapter.notifyDataSetChanged()
    }

    private fun setupListeners() {
        etSearchPatient.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                loadPatients(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        lvPatients.setOnItemClickListener { _, _, index, _ ->
            selectedPatient = patients[index]
            tvId.text = selectedPatient!!.id
            etFirst.setText(selectedPatient!!.firstName)
            etLast.setText(selectedPatient!!.lastName)
            etEmail.setText(selectedPatient!!.email ?: "")
            etPhone.setText(selectedPatient!!.phone ?: "")
        }

        btnCreate.setOnClickListener { createPatient() }
        btnUpdate.setOnClickListener { confirmUpdate() }
        btnDelete.setOnClickListener { confirmDelete() }
        btnClear.setOnClickListener { clearForm() }
    }

    private fun createPatient() {
        if (invalidForm()) return

        val p = Person(
            id = UUID.randomUUID().toString(),
            firstName = etFirst.text.toString().trim(),
            lastName = etLast.text.toString().trim(),
            email = etEmail.text.toString().trim(),
            phone = etPhone.text.toString().trim()
        )

        if (userController.registerPerson(p)) {
            toast("Patient created successfully")
            clearForm()
            loadPatients(etSearchPatient.text.toString())
        }
    }

    private fun updatePatient() {
        val p = selectedPatient ?: return
        if (invalidForm()) return

        p.firstName = etFirst.text.toString().trim()
        p.lastName = etLast.text.toString().trim()
        p.email = etEmail.text.toString().trim()
        p.phone = etPhone.text.toString().trim()

        if (userController.updatePerson(p)) {
            toast("Patient updated successfully")
            clearForm()
            loadPatients(etSearchPatient.text.toString())
        }
    }

    private fun deletePatient() {
        val p = selectedPatient ?: return
        if (userController.deletePerson(p.id)) {
            toast("Patient deleted successfully")
            clearForm()
            loadPatients(etSearchPatient.text.toString())
        }
    }

    private fun invalidForm(): Boolean {
        val invalid = etFirst.text.isBlank() || etLast.text.isBlank()
        if (invalid) toast("Please fill first and last name")
        return invalid
    }

    private fun confirmUpdate() {
        if (selectedPatient == null) {
            toast("Please select a patient first")
            return
        }
        AlertDialog.Builder(this)
            .setTitle("Confirm update")
            .setMessage("Do you really want to update this patient?")
            .setPositiveButton("Yes") { _, _ -> updatePatient() }
            .setNegativeButton("No", null)
            .show()
    }

    private fun confirmDelete() {
        if (selectedPatient == null) {
            toast("Please select a patient first")
            return
        }
        AlertDialog.Builder(this)
            .setTitle("Confirm delete")
            .setMessage("Do you really want to delete this patient?")
            .setPositiveButton("Yes") { _, _ -> deletePatient() }
            .setNegativeButton("No", null)
            .show()
    }

    private fun clearForm() {
        selectedPatient = null
        tvId.text = ""
        etFirst.text.clear()
        etLast.text.clear()
        etEmail.text.clear()
        etPhone.text.clear()
    }

    private fun toast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}