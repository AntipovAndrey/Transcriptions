package ru.andrey.toipa

import android.app.Application
import ru.andrey.toipa.di.ApplicationComponent
import ru.andrey.toipa.di.DaggerApplicationComponent
import ru.andrey.toipa.di.transcription.DaggerTranscriptionComponent
import ru.andrey.toipa.di.transcription.TranscriptionComponent

class App : Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
    }

    val transcriptionComponent: TranscriptionComponent by lazy {
        DaggerTranscriptionComponent.builder()
            .applicationComponent(appComponent)
            .build()
    }
}
