package com.example.miniproject.database

import androidx.room.*
import com.example.miniproject.data.calender.DayItem

@Dao
interface DataAccessObject {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DayItem)

    @Delete
    suspend fun delete(item: DayItem)

    @Query("SELECT EXISTS(SELECT * from days WHERE id = :id)")
    suspend fun isSelected(id: Long): Boolean

    @Query("SELECT COUNT(*) FROM days")
    suspend fun getAll(): Int
}