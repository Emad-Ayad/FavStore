package com.example.test.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.test.data.datasource.remote.ProductsService

object RetrofitHelper {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://dummyjson.com/")
        .build()

    val retrofitService : ProductsService = retrofit.create(ProductsService::class.java)
}