package controller

import data.manager.DataManager
import data.model.Doctor

class DoctorController(private val dataManager: DataManager) {
    fun addDoctor(d: Doctor) = dataManager.createDoctor(d)
    fun getDoctor(id: String) = dataManager.getDoctor(id)
    fun listDoctors() = dataManager.listDoctors()
}