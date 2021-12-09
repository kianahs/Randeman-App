package com.example.atry.data.remote.di

import com.example.atry.data.remote.RetroApi
import com.plcoding.ktorclientandroid.data.remote.HttpRoutes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetroApi():RetroApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(RetroApi.BASE_URL)
            .build()
            .create(RetroApi::class.java)
    }
}