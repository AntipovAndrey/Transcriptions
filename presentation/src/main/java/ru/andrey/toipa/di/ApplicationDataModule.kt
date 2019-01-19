package ru.andrey.toipa.di

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import ru.andrey.data.db.copier.DatabaseCopier
import javax.inject.Singleton

@Module
class ApplicationDataModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient()

    @Singleton
    @Provides
    fun provideDatabaseCopier(context: Context) = DatabaseCopier(context)
}