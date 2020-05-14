package com.example.dusigrosz.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.dusigrosz.PeopleRepository
import com.example.dusigrosz.dao.PeopleDao
import com.example.dusigrosz.db.PeopleRoomDatabase
import com.example.dusigrosz.entities.Person
import java.lang.IllegalArgumentException

class PeopleContentProvider: ContentProvider() {

    private val PEOPLE = 1
    private val PEOPLE_ID = 2

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    private lateinit var peopleRepository: PeopleRepository
    private lateinit var peopleDao: PeopleDao

    init {
        uriMatcher.addURI(AUTHORITY, PEOPLE_TABLE, PEOPLE)
        uriMatcher.addURI(AUTHORITY, "$PEOPLE_TABLE/#", PEOPLE_ID)
    }

    companion object {
        val AUTHORITY = "com.example.dusigrosz.provider.PeopleContentProvider"
        private val PEOPLE_TABLE = "people_table"
        val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$PEOPLE_TABLE")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val uriType = uriMatcher.match(uri)

        val id: Long
        when (uriType) {
            PEOPLE -> {
                id = peopleDao.insert(Person(values!!.getAsString("name"), values.getAsDouble("debt")))
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        context?.contentResolver?.notifyChange(uri, null)
        return Uri.parse("$PEOPLE_TABLE/$id")
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val uriType = uriMatcher.match(uri)

        val cursor: Cursor
        cursor = when(uriType) {
            PEOPLE_ID -> peopleDao.getPersonById(uri.lastPathSegment?.toInt()!!)
            PEOPLE -> peopleDao.getAll()
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }

        cursor.setNotificationUri(context?.contentResolver, uri)
        return cursor
    }

    override fun onCreate(): Boolean {

        peopleDao = PeopleRoomDatabase.getDatabase(context!!, null).peopleDao()
        peopleRepository = PeopleRepository.getInstance(peopleDao)

        return true
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {

        when (uriMatcher.match(uri)) {
            PEOPLE -> throw IllegalArgumentException("Invalid URI: Cannot update without ID; $uri")
            PEOPLE_ID -> {
                if (context == null) return 0

                val person = Person(values!!.getAsString("name"), values.getAsDouble("debt"))
                person.id = uri.lastPathSegment?.toInt()!!

                val count = peopleDao.update(person)

                context?.contentResolver?.notifyChange(uri, null)

                return count
            }
            else -> throw IllegalArgumentException("Unknown URI")
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {

        when (uriMatcher.match(uri)) {
            PEOPLE -> throw IllegalArgumentException("Invalid URI: Cannot update without ID; $uri")
            PEOPLE_ID -> {
                if (context == null) return 0

                val person = Person("", 0.0)
                person.id = uri.lastPathSegment?.toInt()!!

                val count = peopleDao.delete(person)

                context?.contentResolver?.notifyChange(uri, null)

                return count
            }
            else -> throw IllegalArgumentException("Unknown URI")
        }
    }

    override fun getType(uri: Uri): String? {
        return when(uriMatcher.match(uri)) {
            PEOPLE -> "vnd.android.cursor.dir/$AUTHORITY.$PEOPLE_TABLE"
            PEOPLE_ID -> "vnd.android.cursor.item/$AUTHORITY.$PEOPLE_TABLE"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }
}