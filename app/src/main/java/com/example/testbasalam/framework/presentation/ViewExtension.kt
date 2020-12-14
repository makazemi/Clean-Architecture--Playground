package com.example.testbasalam.framework.presentation

import android.app.Activity
import android.widget.Toast

fun Activity.displayToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}