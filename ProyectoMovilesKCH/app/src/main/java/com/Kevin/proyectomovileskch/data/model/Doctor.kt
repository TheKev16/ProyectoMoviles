package com.kevin.proyectomovileskch.data.model

data class Doctor(
    val id: String,
    var firstName: String,
    var lastName: String,
    var specialty: String,
    var photoUri: String? = null
)