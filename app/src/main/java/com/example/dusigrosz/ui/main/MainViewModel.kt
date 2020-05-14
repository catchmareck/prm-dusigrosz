package com.example.dusigrosz.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.dusigrosz.PeopleRepository
import com.example.dusigrosz.db.PeopleRoomDatabase
import com.example.dusigrosz.entities.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val peopleRepository: PeopleRepository

    init {
        val peopleDao = PeopleRoomDatabase.getDatabase(application, viewModelScope).peopleDao()
        peopleRepository = PeopleRepository.getInstance(peopleDao)
    }

    fun getPeople(): LiveData<MutableList<Person>> {
        return peopleRepository.getPeople()
    }

    fun getDebtSum(): Double {
        return peopleRepository.getDebtSum()
    }

    fun deletePerson(person: Person) = viewModelScope.launch(Dispatchers.IO) {
        peopleRepository.deletePerson(person)
    }
}
