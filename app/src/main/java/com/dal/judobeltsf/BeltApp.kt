package com.dal.judobeltsf

import android.app.Application
import androidx.room.Room
import com.dal.judobeltsf.database.AppDatabase

class BeltApp : Application() {
    /*
      val database = Room
        .databaseBuilder(this, AppDatabase::class.java, "app_database")
        .build()
*/


    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }

}