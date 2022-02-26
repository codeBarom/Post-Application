package com.codebaron.mvvmpattern.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.annotation.NonNull

//check for network connectivity and return a boolean
fun isNetworkAvailable(@NonNull context: Context): Boolean {
    val connectMgr: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectMgr.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}

fun errorToast(context: Context, msg: String, lent: Int) {
    Toast.makeText(context, "$msg \uD83D\uDE12", lent).show()
}

fun successToast(context: Context, msg: String, lent: Int) {
    Toast.makeText(context, "$msg \uD83D\uDE00", lent).show()
}