package com.example.dusigrosz.ui.main

import androidx.lifecycle.ViewModel
import java.util.ArrayList
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private val people: ArrayList<Person> = arrayListOf(
        Person(1, "Ala Makota", 79.8),
        Person(2, "Ala Makota", 123.8),
        Person(3, "Ala Makota", 318.0),
        Person(4, "Ala Makota", 23.9)
    )

    fun getPeople(): List<Person> {
        return people
    }

    fun getPerson(position: Int): Person {
        return people[position]
    }

    fun addPerson(name: String, debt: Double) {
        people.add(Person(Random.nextInt(), name, debt))
    }

    fun addPerson(person: Person) {
        people.add(person)
    }

    fun deletePerson(id: Int) {
        people.removeIf { p: Person -> p.id == id }
    }

    fun deletePerson(person: Person) {
        people.removeIf { p: Person -> p.id == person.id }
    }
}
