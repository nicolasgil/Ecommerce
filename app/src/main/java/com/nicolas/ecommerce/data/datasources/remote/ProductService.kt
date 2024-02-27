package com.nicolas.ecommerce.data.datasources.remote

import com.nicolas.ecommerce.data.datasources.remote.remotemodel.ProductRemoteResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {

    @GET("products/")
    suspend fun getProducts(): Response<ProductRemoteResponse>

    @GET("products/categories")
    suspend fun getCategories(): Response<List<String>>

}