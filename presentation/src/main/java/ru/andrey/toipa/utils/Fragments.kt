package ru.andrey.toipa.utils

import android.support.v4.app.Fragment
import ru.andrey.toipa.App

val Fragment.app: App
    get() = (activity!!.application as App)
