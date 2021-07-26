package com.example.rommapplication.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.text.DateFormat

@Entity(tableName = "tasks_table")
@Parcelize
data class Task (
    val name: String,
    val complete: Boolean = false,
    val important: Boolean = false,
    val created: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true) val id: Int = 0
): Parcelable {

    val createdDateFormat: String
        get() = DateFormat.getDateTimeInstance().format(created)
}