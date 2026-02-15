package com.example.test.data.datasource.remote

import com.example.test.data.model.ProductsResponse
import com.example.test.data.network.RetrofitHelper

class RemoteDataSource {
    private val productsService : ProductsService

    init {
        productsService = RetrofitHelper.retrofitService
    }

    suspend fun getProducts(): ProductsResponse {
        return productsService.getProducts()
    }
}