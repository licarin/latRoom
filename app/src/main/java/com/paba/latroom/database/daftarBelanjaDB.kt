package com.paba.latroom.database

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [daftarbelanja::class, historyBelanja::class], version = 1)

abstract class daftarBelanjaDB : RoomDatabase() {
    abstract fun fundaftarBelanjaDAO(): daftarBelanjaDAO
    abstract fun funHistoryBelanjaDAO() : historyBelanjaDAO

    companion object {
        @Volatile
        private var Instance: daftarBelanjaDB? = null

        @JvmStatic
        fun getDatabase(context: Context): daftarBelanjaDB {
            if (Instance == null) {
                synchronized(daftarBelanjaDB::class.java) {
                    Instance = Room.databaseBuilder(
                        context.applicationContext, daftarBelanjaDB::class.java, "daftarBelanja_db"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return Instance as daftarBelanjaDB
        }
    }
}