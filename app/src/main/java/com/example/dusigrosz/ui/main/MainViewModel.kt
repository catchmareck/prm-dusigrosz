package com.example.dusigrosz.ui.main

import androidx.lifecycle.ViewModel
import java.util.ArrayList

class MainViewModel : ViewModel() {

    fun getPeople(): List<Person> {

        val list = ArrayList<Person>()
        list.add(Person("Ala Makota", 79.8))
        list.add(Person("Ala Makota", 123.8))
        list.add(Person("Ala Makota", 318.0))
        list.add(Person("Ala Makota", 23.9))

        return list
    }
}
