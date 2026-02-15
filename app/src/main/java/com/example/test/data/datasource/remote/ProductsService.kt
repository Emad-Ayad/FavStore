package com.example.test.data.datasource.remote

import retrofit2.http.GET
import com.example.test.data.model.ProductsResponse

interface ProductsService {

    @GET("products")
    suspend fun getProducts(): ProductsResponse

}