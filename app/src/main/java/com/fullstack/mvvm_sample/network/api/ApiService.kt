package com.fullstack.mvvm_sample.network.api

import com.fullstack.mvvm_sample.network.models.NetworkData
import com.fullstack.mvvm_sample.network.models.UsersResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ApiService(
    private val api: ApiServiceInterface,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun fetchUserContent(page: Int): ResultWrapper<UsersResponse?> {
        return safeApiCall(dispatcher){
            api.fetchContent(page = page)
        }
    }

}