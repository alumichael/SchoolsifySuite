package com.fbn.fbnquest.di


import android.content.Context
import com.fbn.fbnquest.data.network.RetrofitClient
import com.fbn.fbnquest.data.network.ApiInterface
import com.schoolsify.suite.data.network.Userpreference

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(): RetrofitClient {
        return RetrofitClient()
    }

    @Singleton
    @Provides
    fun provideAuthApi(
        retrofitClient: RetrofitClient,
        @ApplicationContext context: Context
    ): ApiInterface {
        return retrofitClient.buildApi(ApiInterface::class.java, context)
    }


    @Singleton
    @Provides
    fun provideUserPreferences(@ApplicationContext context: Context): Userpreference {
        return Userpreference(context)
    }

}