package com.example.dusigrosz.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.dusigrosz.dao.PeopleDao
import com.example.dusigrosz.entities.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Person::class], version = 1, exportSchema = false)
abstract class PeopleRoomDatabase: RoomDatabase() {

    abstract fun peopleDao(): PeopleDao

    companion object {

        @Volatile
        private var INSTANCE: PeopleRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): PeopleRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PeopleRoomDatabase::class.java,
                    "people_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class PeopleDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.peopleDao())
                }
            }
        }

        suspend fun populateDatabase(peopleDao: PeopleDao) {
            peopleDao.deleteAll()

            peopleDao.addPerson(Person("Ala Makota Live from DB", 79.8))
            peopleDao.addPerson(Person("Ala Makota", 123.8))
            peopleDao.addPerson(Person("Ala Makota", 318.0))
            peopleDao.addPerson(Person("Ala Makota", 23.9))
        }
    }
}
