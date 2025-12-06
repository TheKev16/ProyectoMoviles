package com.kevin.proyectomovileskch.data.model

enum class AppointmentStatus { PENDING, CONFIRMED, COMPLETED, CANCELED }

data class Appointment(
    val id: String,
    val patientId: String,
    var doctorId: String,
    var datetime: Long,
    var notes: String? = null,
    var status: AppointmentStatus = AppointmentStatus.PENDING
)