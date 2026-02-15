package com.example.test.data.repo

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.test.data.datasource.local.LocalDataSource
import com.example.test.data.datasource.remote.RemoteDataSource
import com.example.test.data.model.Product


class ProductsRepo(context: Context) {

    private val localDataSource : LocalDataSource
    private val remoteDataSource : RemoteDataSource


    init {
        localDataSource = LocalDataSource(context)
        remoteDataSource = RemoteDataSource()
    }

    suspend fun getAllProducts() : List<Product>{
        return remoteDataSource.getProducts().products
    }

    val favProducts : LiveData<MutableList<Product>>
        get() =  localDataSource.favProducts


    suspend fun insertFavProduct(product: Product) {
        localDataSource.insertFavProduct(product)
    }

    suspend fun deleteFavProduct(product: Product) {
        localDataSource.deleteFavProduct(product)
    }




}