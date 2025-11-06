package controller

import data.manager.DataManager
import data.model.Person

class UserController(private val dataManager: DataManager) {
    fun registerPerson(p: Person) = dataManager.createPerson(p)
    fun updatePerson(p: Person) = dataManager.updatePerson(p)
    fun getPerson(id: String) = dataManager.getPerson(id)
    fun listPersons() = dataManager.listPersons()
}