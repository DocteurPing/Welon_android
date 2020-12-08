package com.welon.android.databases.daos

import androidx.room.*
import com.welon.android.databases.entities.Restaurant


@Dao
abstract class RestaurantDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(restaurant: Restaurant): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(restaurant: Restaurant)

    @Delete
    abstract fun delete(restaurant: Restaurant)

    @Query("SELECT * FROM restaurants WHERE serverId = :id ORDER BY name ASC")
    abstract fun getByServerId(id: String): Restaurant?

    @Query("SELECT * FROM restaurants WHERE serverId = :name")
    abstract fun getByName(name: String): Restaurant?

    @Query("SELECT * FROM restaurants ORDER BY rank ASC")
    abstract fun getAll(): List<Restaurant>?

    @Query("DELETE FROM restaurants")
    abstract fun nukeTable()
}