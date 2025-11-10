package com.kevin.proyectomovileskch.data.manager

import com.kevin.proyectomovileskch.data.model.Appointment
import com.kevin.proyectomovileskch.data.model.Doctor
import com.kevin.proyectomovileskch.data.model.Person

interface DataManager {
    // Patients / Persons
    fun createPerson(person: Person): Boolean
    fun getPerson(id: String): Person?
    fun updatePerson(person: Person): Boolean
    fun deletePerson(id: String): Boolean
    fun listPersons(): List<Person>

    // Doctors
    fun createDoctor(doctor: Doctor): Boolean
    fun getDoctor(id: String): Doctor?
    fun updateDoctor(doctor: Doctor): Boolean
    fun deleteDoctor(id: String): Boolean
    fun listDoctors(): List<Doctor>

    // Appointments
    fun createAppointment(app: Appointment): Boolean
    fun getAppointment(id: String): Appointment?
    fun updateAppointment(app: Appointment): Boolean
    fun deleteAppointment(id: String): Boolean
    fun listAppointmentsForPatient(patientId: String): List<Appointment>
    fun listAppointmentsForDoctor(doctorId: String): List<Appointment>
}
