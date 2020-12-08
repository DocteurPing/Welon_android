package com.welon.android.databases.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
class User : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "serverId")
    var serverId: String? = null

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "address")
    var address: String? = null
}