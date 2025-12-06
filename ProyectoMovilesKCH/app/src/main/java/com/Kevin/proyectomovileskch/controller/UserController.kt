package com.kevin.proyectomovileskch.controller

import com.kevin.proyectomovileskch.data.manager.DataManager
import com.kevin.proyectomovileskch.data.model.Person

class UserController(private val dataManager: DataManager) {
    fun registerPerson(p: Person) = dataManager.createPerson(p)
    fun updatePerson(p: Person) = dataManager.updatePerson(p)
    fun deletePerson(id: String) = dataManager.deletePerson(id)

    fun getPerson(id: String) = dataManager.getPerson(id)
    fun listPersons() = dataManager.listPersons()
}