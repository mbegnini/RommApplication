package com.example.rommapplication.data

import java.text.DateFormat

data class Task (
    val name: String,
    val complete: Boolean = false,
    val important: Boolean = false,
    val created: Long = System.currentTimeMillis(),
    val id: Int
        ) {

    val createdDateFormat: String
        get() = DateFormat.getDateTimeInstance().format(created)
}