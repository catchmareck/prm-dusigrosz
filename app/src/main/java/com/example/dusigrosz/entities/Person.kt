package com.example.dusigrosz.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "people_table")
data class Person(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "debt") var debt: Double
) : Serializable {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0
}
