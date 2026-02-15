package com.example.test.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ProductsResponse(val products: List<Product>)
@Entity(tableName = "products")
data class Product(
    @PrimaryKey()
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val rating: Double,
    val thumbnail: String
)