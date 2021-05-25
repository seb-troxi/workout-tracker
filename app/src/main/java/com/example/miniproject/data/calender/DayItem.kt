package com.example.miniproject.data.calender

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "days")
data class DayItem(
    @PrimaryKey()
    var id: Long,
    var viewType: Int,
    var month: Int,
    var day: Int,
    var selected: Boolean
) : Parcelable
