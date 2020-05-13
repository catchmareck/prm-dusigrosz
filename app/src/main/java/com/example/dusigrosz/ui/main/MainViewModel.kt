package com.example.dusigrosz.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dusigrosz.PeopleRepository
import java.util.ArrayList

class MainViewModel : ViewModel() {

    private val peopleRepository = PeopleRepository.getInstance()

    fun getPeople(): LiveData<ArrayList<Person>> {
        return peopleRepository.getPeople()
    }

    fun getPerson(position: Int): Person {
        return peopleRepository.getPerson(position)
    }

    fun getDebtSum(): Double {
        return peopleRepository.getDebtSum()
    }

    fun addPerson(name: String, debt: Double) {
        peopleRepository.addPerson(name, debt)
    }

    fun addPerson(person: Person) {
        peopleRepository.addPerson(person)
    }

    fun deletePerson(position: Int) {
        peopleRepository.deletePerson(position)
    }

    fun deletePerson(person: Person) {
        peopleRepository.deletePerson(person)
    }
}
