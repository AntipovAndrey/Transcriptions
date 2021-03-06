package ru.andrey.toipa.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationDataModule::class])
interface ApplicationComponent {

    fun okHttpClient(): OkHttpClient

    fun application(): Application

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}