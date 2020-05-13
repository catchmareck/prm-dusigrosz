package com.example.dusigrosz.ui.edit

import androidx.lifecycle.ViewModel
import com.example.dusigrosz.PeopleRepository
import com.example.dusigrosz.ui.main.Person

class EditViewModel : ViewModel() {

    private val peopleRepository = PeopleRepository.getInstance()

    lateinit var person: Person

    fun updatePerson(name: String, debt: Double) {
        peopleRepository.updatePerson(person, name, debt)
    }
}
