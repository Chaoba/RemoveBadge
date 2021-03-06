package com.mushuichuan.removebadge

import android.content.ComponentName
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.util.Log

/**
 * Created by Liyanshun on 2016/4/25.
 */
object Util {

    private val TAG = "Util"

    fun sendToOneApp(context: Context, packageName: String, className: String, number: Int) {
        when (PhoneTypeManager.PHONE_TYPE) {
            PhoneTypeManager.TYPE_SUMSUNG -> sendToSamSumg(context, packageName, className, number)
            PhoneTypeManager.TYPE_HTC -> sendToHtc(context, packageName, className, number)
            PhoneTypeManager.TYPE_SONY -> sendToSony(context, packageName, className, number)
        }
    }

    fun sendToAll(context: Context, number: Int) {
        when (PhoneTypeManager.PHONE_TYPE) {
            PhoneTypeManager.TYPE_SUMSUNG -> sendToSamSungAllContentResolver(context, number)
            PhoneTypeManager.TYPE_HTC -> sendToHtcAll(context, number)
            PhoneTypeManager.TYPE_SONY -> sendToSonyAll(context, number)
        }
    }

    fun sendToSamSumg(context: Context, packageName: String, className: String, number: Int) {
        Log.d(TAG, "packageName:$packageName")
        Log.d(TAG, "className:$className")
        Log.d(TAG, "number:$number")
        val badgeIntent = Intent(Actions.ACTION_SUMSUNG)
        badgeIntent.putExtra("badge_count", number)
        badgeIntent.putExtra("badge_count_package_name", packageName)
        badgeIntent.putExtra("badge_count_class_name", className)
        context.sendBroadcast(badgeIntent)
    }

    /**
     * send broadcast to all apps, not recommend because it is low efficiency
     */
    fun sendToSamSungAllBroadCast(context: Context, number: Int) {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        val list = context.packageManager.queryIntentActivities(intent, PackageManager.GET_ACTIVITIES);
        for (resolveInfo in list) {
            val activityName = resolveInfo.activityInfo.name
            val packageName = resolveInfo.activityInfo.applicationInfo.packageName
            sendToSamSumg(context, packageName, activityName, number)
        }

    }

    /**
     * directly change the value in ContentProvider
     */
    fun sendToSamSungAllContentResolver(context: Context, number: Int) {
        val uri = Uri.parse("content://com.sec.badge/apps")
        val contentResolver = context.contentResolver
        val c: Cursor? = contentResolver.query(uri, null, null, null, null) ?: return
        try {
            var where = "package in ("
            while (c!!.moveToNext()) {
                val pkg = c.getString(1)
                val clazz = c.getString(2)
                val badgeCount = c.getInt(3)
                Log.d(TAG, "package: $pkg, class: $clazz, badgeCount: $badgeCount")
                where += "'$pkg',"
            }
            where += "' ')"
            Log.d(TAG, "update $where to number:　$number")
            val cv = ContentValues()
            cv.put("badgeCount", number)

            contentResolver.update(Uri.parse("content://com.sec.badge/apps"), cv, where, null)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            c!!.close()
        }
    }

    fun sendToHtc(context: Context, packageName: String, className: String, number: Int) {
        Log.d(TAG, "sendToHtc $packageName,number:$number")
        val setNotificationIntent = Intent(Actions.ACTION_HTC);
        val localComponentName = ComponentName(packageName, className);
        setNotificationIntent.putExtra("com.htc.launcher.extra.COMPONENT", localComponentName.flattenToShortString());
        setNotificationIntent.putExtra("com.htc.launcher.extra.COUNT", number);
        context.sendBroadcast(setNotificationIntent);
    }

    fun sendToHtcAll(context: Context, number: Int) {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        val list = context.packageManager.queryIntentActivities(intent, PackageManager.GET_ACTIVITIES);
        for (resolveInfo in list) {
            val packageName = resolveInfo.activityInfo.applicationInfo.packageName
            val activityName = resolveInfo.activityInfo.name
            sendToHtc(context, packageName, activityName, number)
        }
    }

    fun sendToSony(context: Context, packageName: String, className: String, number: Int) {
        Log.d(TAG, "sendToSony $packageName")
        val intent = Intent(Actions.ACTION_SONY);
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", className);
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", number != 0);
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", number.toString());
        intent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", packageName);
        context.sendBroadcast(intent);
    }

    fun sendToSonyAll(context: Context, number: Int) {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        val list = context.packageManager.queryIntentActivities(intent, PackageManager.GET_ACTIVITIES);
        for (resolveInfo in list) {
            val activityName = resolveInfo.activityInfo.name
            val packageName = resolveInfo.activityInfo.applicationInfo.packageName
            sendToSony(context, packageName, activityName, number)
        }

    }
}
