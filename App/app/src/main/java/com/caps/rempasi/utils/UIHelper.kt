package com.caps.rempasi.utils

import android.content.Context
import android.widget.Toast
import com.caps.rempasi.R

object UIHelper {
    fun showToastPermission(context: Context) {
        Toast.makeText(
            context,
            context.getString(R.string.permission_message),
            Toast.LENGTH_LONG
        ).show()
    }
}