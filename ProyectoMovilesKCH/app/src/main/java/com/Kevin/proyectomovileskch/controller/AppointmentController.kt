package com.kevin.proyectomovileskch.controller

import com.kevin.proyectomovileskch.data.manager.DataManager
import com.kevin.proyectomovileskch.data.model.Appointment

class AppointmentController(private val dataManager: DataManager) {

    fun createAppointment(appointment: Appointment): Boolean =
        dataManager.createAppointment(appointment)

    fun getAppointment(id: String): Appointment? =
        dataManager.getAppointment(id)

    fun updateAppointment(appointment: Appointment): Boolean =
        dataManager.updateAppointment(appointment)

    fun deleteAppointment(id: String): Boolean =
        dataManager.deleteAppointment(id)

    fun listAppointmentsForPatient(patientId: String): List<Appointment> =
        dataManager.listAppointmentsForPatient(patientId)

    fun listAppointmentsForDoctor(doctorId: String): List<Appointment> =
        dataManager.listAppointmentsForDoctor(doctorId)
}