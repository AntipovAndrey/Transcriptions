package ru.andrey.toipa.di.transcription

import dagger.Component
import ru.andrey.toipa.di.ApplicationComponent
import ru.andrey.toipa.di.scope.Feature
import ru.andrey.toipa.di.viewmodel.ViewModelAwareComponent

@Feature
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [TranscriptionModule::class, TranscriptionViewModelModule::class]
)
interface TranscriptionComponent : ViewModelAwareComponent
