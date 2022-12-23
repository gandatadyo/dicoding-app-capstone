package com.aplikasi.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aplikasi.core.data.source.local.entity.MovieEntity
import com.aplikasi.core.data.source.local.entity.MovieSearchEntity

@Database(entities = [
    MovieEntity::class,
    MovieSearchEntity::class,
], version = 2,exportSchema = false)

abstract  class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "appcapstone.db"
                )
//                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}