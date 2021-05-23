package com.example.miniproject.data.calender

import android.os.Parcelable
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.util.*

@Parcelize
data class asd(
    @PrimaryKey()
    var date: Calendar
) : Parcelable

class Converter {
    @TypeConverter
    fun toDate(e: Long): Date {
        return Date(e)
    }

    @TypeConverter
    fun fromDate(e: Date): Long {
        return e.time
    }
}