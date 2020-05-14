package com.example.dusigrosz.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dusigrosz.entities.Person

@Dao
interface PeopleDao {

    @Query("SELECT * FROM people_table")
    fun getPeople(): LiveData<MutableList<Person>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPerson(person: Person)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

    @Query("DELETE FROM people_table")
    suspend fun deleteAll()
}
