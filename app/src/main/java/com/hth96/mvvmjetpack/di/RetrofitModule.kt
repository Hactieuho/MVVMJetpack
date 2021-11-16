package com.hth96.mvvmjetpack.di

import com.hth96.mvvmjetpack.api.NullOnEmptyConverterFactory
import com.hth96.mvvmjetpack.api.UserService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class BaseUrl

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {
    @Provides
    @Singleton
    fun provideUserService(
        @BaseUrl baseUrl: String,
        moshi: Moshi,
        httpClient: OkHttpClient.Builder,
        nullOnEmptyConverterFactory: NullOnEmptyConverterFactory) : UserService {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addConverterFactory(nullOnEmptyConverterFactory)
            .client(httpClient.build())
            .build()
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder() : OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.readTimeout(10, TimeUnit.SECONDS)
        httpClient.connectTimeout(10, TimeUnit.SECONDS)
        return httpClient
    }

    @BaseUrl
    @Provides
    @Singleton
    fun provideBaseUrl(): String = "https://reqres.in/api/"
}