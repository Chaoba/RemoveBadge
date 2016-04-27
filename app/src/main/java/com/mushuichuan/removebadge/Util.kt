package com.mushuichuan.removebadge

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log

/**
 * Created by Liyanshun on 2016/4/25.
 */
object Util {

    private val TAG = "Util"

    fun sendToSamSumg(context: Context, packageName: String, className: String, number: Int, test: Boolean) {
        Log.d(TAG, "packageName:$packageName")
        Log.d(TAG, "className:$className")
        Log.d(TAG, "number:$number")
        val localIntent = Intent(Actions.ACTION_SUMSUNG)
        localIntent.putExtra("badge_count", number)
        localIntent.putExtra("badge_count_package_name", packageName)
        localIntent.putExtra("badge_count_class_name", className)
        localIntent.putExtra("isTest", test)
        context.sendBroadcast(localIntent)
    }

    fun sendToSamSumgAll(context: Context, number: Int, test: Boolean) {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        val list = context.packageManager.queryIntentActivities(intent, PackageManager.GET_ACTIVITIES);
        for (resolveInfo in list) {
            val activityName = resolveInfo.activityInfo.name
            val packageName = resolveInfo.activityInfo.applicationInfo.packageName
            sendToSamSumg(context, packageName, activityName, number, test)
        }

    }
}
