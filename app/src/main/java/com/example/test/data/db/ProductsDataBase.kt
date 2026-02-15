package com.example.test.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.test.data.model.Product
import com.example.test.data.datasource.local.ProductsDao


@Database(entities = arrayOf(Product::class), version = 1,exportSchema = false)
abstract class ProductsDataBase : RoomDatabase() {
    abstract fun productsDao(): ProductsDao
    companion object {
        @Volatile
        private var INSTANCE: ProductsDataBase? = null

        fun getInstance(ctx: Context): ProductsDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    ProductsDataBase::class.java,
                    "products_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}