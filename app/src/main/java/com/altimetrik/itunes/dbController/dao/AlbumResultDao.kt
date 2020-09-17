package com.altimetrik.itunes.dbController.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.altimetrik.itunes.dbController.entity.AlbumResultsEntity

@Dao
interface AlbumResultDao {
    @Query("SELECT * FROM results")
    fun fetchAll(): List<AlbumResultsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll( albumResult: List<AlbumResultsEntity>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert( albumResult: AlbumResultsEntity?)
}