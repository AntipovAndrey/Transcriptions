package ru.andrey.toipa.utils

import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher

inline fun textChanged(crossinline listener: (String) -> Unit) = textChangedEditable { listener(it.toString()) }

inline fun textChangedEditable(crossinline listener: (Editable) -> Unit) = object : TextWatcher {

    override fun afterTextChanged(s: Editable) {
        listener(s)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}


infix fun TextWatcher.debounce(millis: Long): TextWatcher {
    var task = Runnable {}
    val handler = Handler(Looper.getMainLooper())
    return textChangedEditable {
        handler.removeCallbacks(task)
        task = Runnable { this.afterTextChanged(it) }
        handler.postDelayed(task, millis)
    }
}
