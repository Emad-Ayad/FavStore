package com.example.test.data.datasource.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.test.data.model.Product
import com.example.test.data.db.ProductsDataBase



class LocalDataSource(context: Context) {

    private val productsDao : ProductsDao

    init {
        val dataBase : ProductsDataBase = ProductsDataBase.getInstance(context)
        productsDao = dataBase.productsDao()
    }

    val favProducts: LiveData<MutableList<Product>>
        get() =  productsDao.products


    suspend fun insertFavProduct(product: Product) {
        productsDao.insertProducts(product)
    }

    suspend fun deleteFavProduct(product: Product) {
        productsDao.deleteProduct(product)
    }


}