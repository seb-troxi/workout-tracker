package com.example.miniproject.data.calender

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class DayItem(
    @PrimaryKey()
    var id: Int,
    var selected: Boolean
) : Parcelable
