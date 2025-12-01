package com.kevin.proyectomovileskch.controller

import com.kevin.proyectomovileskch.data.manager.DataManager
import com.kevin.proyectomovileskch.data.model.Doctor

class DoctorController(private val dataManager: DataManager) {
    fun addDoctor(d: Doctor) = dataManager.createDoctor(d)
    fun getDoctor(id: String) = dataManager.getDoctor(id)
    fun getAllDoctors() = dataManager.listDoctors()
    fun updateDoctor(d: Doctor) = dataManager.updateDoctor(d)
    fun deleteDoctor(id: String) = dataManager.deleteDoctor(id)
}