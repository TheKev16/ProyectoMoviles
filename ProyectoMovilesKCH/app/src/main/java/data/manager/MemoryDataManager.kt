package data.manager

import data.model.Appointment
import data.model.Doctor
import data.model.Person

class MemoryDataManager : DataManager {
    private val persons = mutableMapOf<String, Person>()
    private val doctors = mutableMapOf<String, Doctor>()
    private val appointments = mutableMapOf<String, Appointment>()

    // Persons
    override fun createPerson(person: Person): Boolean {
        if (persons.containsKey(person.id)) return false
        persons[person.id] = person
        return true
    }
    override fun getPerson(id: String): Person? = persons[id]
    override fun updatePerson(person: Person): Boolean {
        if (!persons.containsKey(person.id)) return false
        persons[person.id] = person
        return true
    }
    override fun deletePerson(id: String): Boolean = persons.remove(id) != null
    override fun listPersons(): List<Person> = persons.values.toList()

    // Doctors
    override fun createDoctor(doctor: Doctor): Boolean {
        if (doctors.containsKey(doctor.id)) return false
        doctors[doctor.id] = doctor
        return true
    }
    override fun getDoctor(id: String): Doctor? = doctors[id]
    override fun updateDoctor(doctor: Doctor): Boolean {
        if (!doctors.containsKey(doctor.id)) return false
        doctors[doctor.id] = doctor
        return true
    }
    override fun deleteDoctor(id: String): Boolean = doctors.remove(id) != null
    override fun listDoctors(): List<Doctor> = doctors.values.toList()

    // Appointments
    override fun createAppointment(app: Appointment): Boolean {
        if (appointments.containsKey(app.id)) return false
        appointments[app.id] = app
        return true
    }
    override fun getAppointment(id: String): Appointment? = appointments[id]
    override fun updateAppointment(app: Appointment): Boolean {
        if (!appointments.containsKey(app.id)) return false
        appointments[app.id] = app
        return true
    }
    override fun deleteAppointment(id: String): Boolean = appointments.remove(id) != null
    override fun listAppointmentsForPatient(patientId: String): List<Appointment> =
        appointments.values.filter { it.patientId == patientId }

    override fun listAppointmentsForDoctor(doctorId: String): List<Appointment> =
        appointments.values.filter { it.doctorId == doctorId }
}
