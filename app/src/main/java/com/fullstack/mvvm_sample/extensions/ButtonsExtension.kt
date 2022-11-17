package com.fullstack.mvvm_sample.extensions

import android.widget.Button

fun Button.deactivateButton() {
    isEnabled = false
}

fun Button.activateButton() {
    isEnabled = true
}