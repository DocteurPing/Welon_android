package com.welon.android.databases.daos

import androidx.room.*
import com.welon.android.databases.entities.Menu

@Dao
abstract class MenuDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(menu: Menu): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(menu: Menu)

    @Delete
    abstract fun delete(menu: Menu)

    @Query("SELECT * FROM menus ORDER BY name ASC")
    abstract fun getAll(): List<Menu>?

    @Query("SELECT * FROM menus WHERE serverId = :serverId ORDER BY name ASC")
    abstract fun getAllByServerId(serverId: String): List<Menu>?

    @Query("SELECT * FROM menus WHERE restaurantId = :restaurantId ORDER BY name ASC")
    abstract fun getAllByRestaurantId(restaurantId: String): List<Menu>?

    @Query("DELETE FROM menus")
    abstract fun nukeTable()
}