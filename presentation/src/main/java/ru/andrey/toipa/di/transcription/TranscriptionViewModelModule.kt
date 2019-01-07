package ru.andrey.toipa.di.transcription

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.andrey.toipa.di.viewmodel.ViewModelKey
import ru.andrey.toipa.ui.TranscriptionViewModel

@Module
abstract class TranscriptionViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TranscriptionViewModel::class)
    internal abstract fun transcriptionViewModel(viewModel: TranscriptionViewModel): ViewModel
}
