package com.sky.kahabat.di

import com.sky.kahabat.model.api.QuotesService
import com.sky.kahabat.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RetrofitHelper {


     @Provides
     @Singleton
    fun providesRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesQuotesService(retrofit: Retrofit): QuotesService {
        return retrofit.create(QuotesService::class.java)
    }
}