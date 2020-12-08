package com.welon.android.databases.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "items")
class Item : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "serverId")
    var serverId: String? = null

    @ColumnInfo(name = "restaurantId")
    var restaurantId: String? = null

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "description")
    var description: String? = null

    @ColumnInfo(name = "price")
    var price: String? = null

    @ColumnInfo(name = "type")
    var type: Int? = null

    @ColumnInfo(name = "menuId")
    var menuId: String? = null
}