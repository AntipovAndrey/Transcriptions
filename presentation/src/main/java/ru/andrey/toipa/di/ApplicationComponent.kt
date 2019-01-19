package ru.andrey.toipa.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import okhttp3.OkHttpClient
import ru.andrey.data.db.copier.DatabaseCopier
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationDataModule::class])
interface ApplicationComponent {

    fun okHttpClient(): OkHttpClient

    fun databaseCopier(): DatabaseCopier

    fun context(): Context

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): ApplicationComponent
    }
}