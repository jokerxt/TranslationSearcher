package jxt.translationsearcher.android_util

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

val Context.inputManager: InputMethodManager
    get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

fun Fragment.hideKeyboard() {
    context?.inputManager?.hideSoftInputFromWindow(view?.windowToken, 0)
}