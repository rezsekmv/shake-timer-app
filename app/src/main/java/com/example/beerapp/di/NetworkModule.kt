package com.example.beerapp.di

import android.util.Log
import com.example.beerapp.datasource.network.BeerService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun getRetrofit(client: OkHttpClient, converter: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.punkapi.com/v2/")
            .client(client)
            .addConverterFactory(converter)
            .build()
    }

    @Provides
    fun getConverterFactory(moshi: Moshi): Converter.Factory {
        return MoshiConverterFactory
            .create(moshi)
    }

    @Provides
    fun getMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor { message ->
                Log.d("Interceptor", message)
            }.setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    fun getBeerService(retrofit: Retrofit): BeerService {
        return retrofit.create(BeerService::class.java)
    }
}