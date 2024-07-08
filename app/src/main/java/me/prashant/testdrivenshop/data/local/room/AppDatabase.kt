package me.prashant.testdrivenshop.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import me.prashant.testdrivenshop.data.local.entities.CartItem

@Database(entities = [CartItem::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}
