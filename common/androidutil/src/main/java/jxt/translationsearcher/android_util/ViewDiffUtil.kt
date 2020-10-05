package jxt.translationsearcher.android_util

import android.text.SpannableStringBuilder
import android.widget.EditText
import android.widget.TextView

var TextView.diffedText: CharSequence?
    get() = text
    set(value) {
        if (text != value) text = value
    }

var EditText.diffedText: String
    get() = text.toString()
    set(value) {
        if (text.toString() != value) text = SpannableStringBuilder(value)
    }