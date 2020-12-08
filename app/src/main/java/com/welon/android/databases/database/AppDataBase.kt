package com.welon.android.databases.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.welon.android.databases.daos.ItemDAO
import com.welon.android.databases.daos.MenuDAO
import com.welon.android.databases.daos.RestaurantDAO
import com.welon.android.databases.entities.Item
import com.welon.android.databases.entities.Menu
import com.welon.android.databases.entities.Restaurant

@Database(
    version = 1, entities = [
        Restaurant::class,
        Item::class,
        Menu::class]
)

abstract class AppDatabase : RoomDatabase() {

    companion object {
        private val sLock = Any()
        var INSTANCE: AppDatabase? = null

        fun <T : AppDatabase> init(
            context: Context,
            dataBaseClass: Class<T>,
            databaseName: String
        ) {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        dataBaseClass,
                        databaseName
                    )
                        .allowMainThreadQueries().build()
                }
            }
        }
    }

    abstract fun restaurantDAO(): RestaurantDAO
    abstract fun itemDAO(): ItemDAO
    abstract fun menuDAO(): MenuDAO
}