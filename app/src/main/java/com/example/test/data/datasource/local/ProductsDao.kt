package com.example.test.data.datasource.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test.data.model.Product


@Dao
interface ProductsDao {

    @get:Query("SELECT * FROM products")
    val products : LiveData<MutableList<Product>>

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun insertProducts(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)


}