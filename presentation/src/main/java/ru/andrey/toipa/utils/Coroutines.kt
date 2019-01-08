package ru.andrey.toipa.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.isActive

inline fun CoroutineScope.ifActive(crossinline task: () -> Unit) {
    if (isActive) {
        task()
    }
}
