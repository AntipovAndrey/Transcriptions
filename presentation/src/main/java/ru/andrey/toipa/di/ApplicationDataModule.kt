package ru.andrey.toipa.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class ApplicationDataModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient()
}