package ru.andrey.toipa.utils

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    val contentIfNotHandled: T?
        get() {
            return if (hasBeenHandled) {
                null
            } else {
                hasBeenHandled = true
                content
            }
        }
}

inline fun <reified T> T.toEvent() = Event(this)
