package com.mushuichuan.removebadge

import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Created by Liyanshun on 2016/4/25.
 */
object Util {

    private val TAG = "Util"

    fun sendToSamsumg(context: Context, packageName: String, className: String, number: Int) {
        Log.d(TAG, "packageName:" + packageName)
        Log.d(TAG, "className:" + className)
        val localIntent = Intent(Actions.ACTION_SUMSUNG)
        localIntent.putExtra("badge_count", number)
        localIntent.putExtra("badge_count_package_name", packageName)
        localIntent.putExtra("badge_count_class_name", className)
        context.sendBroadcast(localIntent)
    }
}
