package com.example.dusigrosz

import androidx.lifecycle.LiveData
import com.example.dusigrosz.dao.PeopleDao
import com.example.dusigrosz.entities.Person

class PeopleRepository(private val peopleDao: PeopleDao) {

    companion object {
        private lateinit var instance: PeopleRepository
        fun getInstance(peopleDao: PeopleDao): PeopleRepository {
            if (!::instance.isInitialized) {
                instance = PeopleRepository(peopleDao)
            }
            return instance
        }
    }

    private val livePeople = peopleDao.getPeople()

    fun getPeople(): LiveData<MutableList<Person>> {
        return livePeople
    }

    fun getDebtSum(): Double {
        return livePeople.value!!.sumByDouble { person: Person -> person.debt }
    }

    suspend fun addPerson(person: Person): Long {
        return peopleDao.addPerson(person)
    }

    suspend fun updatePerson(currentPerson: Person, newName: String, newDebt: Double) {
        currentPerson.name = newName
        currentPerson.debt = newDebt
        peopleDao.updatePerson(currentPerson)
    }

    suspend fun deletePerson(person: Person) {
        peopleDao.deletePerson(person)
    }
}
