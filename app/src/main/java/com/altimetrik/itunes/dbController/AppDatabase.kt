package com.altimetrik.itunes.dbController

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.Room
import com.altimetrik.itunes.dbController.dao.AlbumResultDao
import com.altimetrik.itunes.dbController.entity.AlbumResultsEntity
import com.altimetrik.itunes.helper.ApplicationClass

@Database(entities = [
    AlbumResultsEntity::class],
        version = 6, exportSchema = false)
abstract class AppDatabase() : RoomDatabase() {
    companion object {
        const val DB_NAME = "itunes_app.db"
        private var mAppDatabase: AppDatabase? = null
        private fun provideDatabase(): AppDatabase {
            if (mAppDatabase == null) {
                mAppDatabase = Room.databaseBuilder(ApplicationClass.appContext,
                        AppDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return mAppDatabase as AppDatabase
        }

        fun getInstance(): AppDatabase {
            return mAppDatabase ?: synchronized(this) {
                mAppDatabase ?: provideDatabase().also { mAppDatabase = it }
            }
        }

    }

    abstract fun getAlbumResult(): AlbumResultDao
}
