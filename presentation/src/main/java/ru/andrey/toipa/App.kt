package ru.andrey.toipa

import android.app.Application
import android.support.text.emoji.EmojiCompat
import android.support.text.emoji.bundled.BundledEmojiCompatConfig
import ru.andrey.toipa.di.ApplicationComponent
import ru.andrey.toipa.di.DaggerApplicationComponent
import ru.andrey.toipa.di.transcription.DaggerTranscriptionComponent
import ru.andrey.toipa.di.transcription.TranscriptionComponent

class App : Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .context(this)
            .build()
    }

    val transcriptionComponent: TranscriptionComponent by lazy {
        DaggerTranscriptionComponent.builder()
            .applicationComponent(appComponent)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        super.onCreate()
        EmojiCompat.init(BundledEmojiCompatConfig(this))
        appComponent.databaseCopier().copy()
    }
}
