package com.fullstack.mvvm_sample.di

import com.fullstack.mvvm_sample.network.api.ApiService
import com.fullstack.mvvm_sample.network.api.ApiServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Provides
    fun provideCraveApi(@BaseApiInterface apiRetrofitInterface: ApiServiceInterface): ApiService {
        return ApiService(
            api = apiRetrofitInterface
        )
    }

}