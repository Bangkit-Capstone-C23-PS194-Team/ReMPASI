package com.caps.rempasi.utils

import android.content.Context
import android.widget.Toast

object UIHelper {
    fun showToastPermission(context: Context) {
        Toast.makeText(
            context,
            "Anda perlu mengaktifkan izin dalam pengaturan aplikasi",
            Toast.LENGTH_LONG
        ).show()
    }
}