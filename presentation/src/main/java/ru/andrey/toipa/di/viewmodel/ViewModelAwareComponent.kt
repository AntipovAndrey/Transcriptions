package ru.andrey.toipa.di.viewmodel

interface ViewModelAwareComponent {

    fun viewModelFactory(): ViewModelFactory
}
