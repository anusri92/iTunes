package com.altimetrik.itunes.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object AppUtility {
    @JvmStatic
    fun isNetworkOnline(context: Context): Boolean {
        var status = false
        try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var netInfo = cm.getNetworkInfo(0)
            if (netInfo != null && netInfo.state == NetworkInfo.State.CONNECTED) {
                status = true
            } else {
                netInfo = cm.getNetworkInfo(1)
                if (netInfo != null && netInfo.state == NetworkInfo.State.CONNECTED) status = true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return status
    }
}