package com.example.dusigrosz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dusigrosz.ui.main.Person
import java.util.ArrayList
import kotlin.random.Random

class PeopleRepository {

    companion object {
        private lateinit var instance: PeopleRepository
        fun getInstance(): PeopleRepository {
            if (!::instance.isInitialized) {
                instance = PeopleRepository()
            }
            return instance
        }
    }

    private val people: ArrayList<Person> = arrayListOf(
        Person(1, "Ala Makota", 79.8),
        Person(2, "Ala Makota", 123.8),
        Person(3, "Ala Makota", 318.0),
        Person(4, "Ala Makota", 23.9)
    )

    private val livePeople = MutableLiveData<ArrayList<Person>>()

    init {
        livePeople.value = arrayListOf(
            Person(1, "Ala Makota Live", 79.8),
            Person(2, "Ala Makota", 123.8),
            Person(3, "Ala Makota", 318.0),
            Person(4, "Ala Makota", 23.9)
        )
    }

    fun getPeople(): LiveData<ArrayList<Person>> {
        return livePeople
    }

    fun getPerson(position: Int): Person {
        return livePeople.value!![position]
    }

    fun findPerson(name: String): Person? {
        return livePeople.value!!.find { person -> person.name == name }
    }

    fun getDebtSum(): Double {
        return livePeople.value!!.sumByDouble { person: Person -> person.debt }
    }

    fun addPerson(name: String, debt: Double) {
        livePeople.value!!.add(Person(Random.nextInt(), name, debt))
        changeList()
    }

    fun addPerson(person: Person) {
        livePeople.value!!.add(person)
        changeList()
    }

    fun updatePerson(currentPerson: Person, newName: String, newDebt: Double) {
        val currentList = livePeople.value!!
        val index = currentList.indexOfFirst { person -> person.id == currentPerson.id }
        currentList[index].name = newName
        currentList[index].debt = newDebt

        changeList()
    }

    fun deletePerson(position: Int) {
        livePeople.value!!.removeAt(position)
        changeList()
    }

    fun deletePerson(person: Person) {
        livePeople.value!!.removeIf { p: Person -> p.id == person.id }
        changeList()
    }

    private fun changeList() {
        val currentList = livePeople.value!!
        val newList = ArrayList<Person>()
        newList.addAll(currentList)
        livePeople.value = newList
    }
}
