package com.welon.android.databases.daos

import androidx.room.*
import com.welon.android.databases.entities.Item

@Dao
abstract class ItemDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(item: Item): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(item: Item)

    @Delete
    abstract fun delete(item: Item)

    @Query("SELECT * FROM items ORDER BY name ASC")
    abstract fun getAll(): List<Item>?

    @Query("SELECT * FROM items WHERE type = :type ORDER BY name ASC")
    abstract fun getAllByType(type: Int): List<Item>?

    @Query("SELECT * FROM items WHERE restaurantId = :id ORDER BY name ASC")
    abstract fun getAllByRestaurantId(id: String): List<Item>?

    @Query("SELECT * FROM items WHERE menuId = :id ORDER BY name ASC")
    abstract fun getAllByMenuId(id: String): List<Item>?

    @Query("SELECT * FROM items WHERE type = :type AND restaurantId = :id ORDER BY name ASC")
    abstract fun getAllByTypeAndRestaurantId(type: Int, id: String): List<Item>?

    @Query("SELECT * FROM items WHERE serverId = :serverId ORDER BY name ASC")
    abstract fun getItemByServerId(serverId: String): Item

    @Query("DELETE FROM items")
    abstract fun nukeTable()
}