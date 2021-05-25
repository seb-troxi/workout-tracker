package com.example.miniproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.miniproject.data.calender.DayItem

@Database(entities = [DayItem::class], version = 1, exportSchema = false)
abstract class DayItemDatabase : RoomDatabase() {
    abstract val database: DataAccessObject

    companion object {

        @Volatile
        private var INSTANCE: DayItemDatabase? = null

        fun getInstance(context: Context): DayItemDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            DayItemDatabase::class.java,
                            "saved_days_database"
                    )
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}