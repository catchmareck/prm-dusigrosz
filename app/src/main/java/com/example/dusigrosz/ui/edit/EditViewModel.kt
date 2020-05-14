package com.example.dusigrosz.ui.edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.dusigrosz.PeopleRepository
import com.example.dusigrosz.db.PeopleRoomDatabase
import com.example.dusigrosz.entities.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditViewModel(application: Application) : AndroidViewModel(application) {

    private val peopleRepository: PeopleRepository

    init {
        val peopleDao = PeopleRoomDatabase.getDatabase(application, viewModelScope).peopleDao()
        peopleRepository = PeopleRepository.getInstance(peopleDao)
    }

    lateinit var person: Person


    fun addPerson(person: Person) = viewModelScope.launch(Dispatchers.IO) {
        peopleRepository.addPerson(person)
    }

    fun updatePerson(name: String, debt: Double) = viewModelScope.launch(Dispatchers.IO) {
        peopleRepository.updatePerson(person, name, debt)
    }
}
