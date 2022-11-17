package com.fullstack.mvvm_sample.network.api

import com.fullstack.mvvm_sample.network.models.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {

    @GET("users")
    suspend fun fetchContent(
        @Query("page") page: Int
    ): Response<UsersResponse>

}