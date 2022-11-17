package com.fullstack.mvvm_sample.di

import android.content.Context
import com.fullstack.mvvm_sample.network.api.ApiServiceInterface
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @NetWorkAPIs
    @Provides
    fun provideOkHttp(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                chain.proceed(request)
            }
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @BaseApiInterface
    @Provides
    fun provideRetrofit(@NetWorkAPIs okHttpClient: OkHttpClient): ApiServiceInterface {
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()
                )
            )
            .build()
        return retrofit.create(ApiServiceInterface::class.java)
    }

    @Provides
    fun provideGsonFactory(): Gson {
        return Gson()
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseApiInterface

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class NetWorkAPIs