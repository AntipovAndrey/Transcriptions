package ru.andrey.toipa.ui

import android.support.v4.app.Fragment
import ru.andrey.toipa.SingleFragmentActivity

class TranscriptionActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment = TranscriptionFragment()

}