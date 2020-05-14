package com.example.dusigrosz.dao

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dusigrosz.entities.Person

@Dao
interface PeopleDao {

    @Query("SELECT * FROM people_table")
    fun getPeople(): LiveData<MutableList<Person>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPerson(person: Person): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

    @Query("DELETE FROM people_table")
    suspend fun deleteAll()

    // For ContentProvider
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(person: Person): Long

    @Query("SELECT * FROM people_table WHERE id = :id")
    fun getPersonById(id: Int): Cursor

    @Query("SELECT * FROM people_table")
    fun getAll(): Cursor

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(person: Person): Int

    @Delete
    fun delete(person: Person): Int
}
