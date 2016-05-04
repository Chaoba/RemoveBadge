package com.mushuichuan.removebadge

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
class RemoveBadgeService : IntentService("RemoveBadgeService") {

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            val action = intent.action
            Log.d(TAG, "Handle action:" + action)
            when (action) {
//                Actions.ACTION_SUMSUNG -> handleActionSamSung(intent)
//                Actions.ACTION_SONY -> handleActionSony(intent)
//                Actions.ACTION_HTC -> handleActionHtc(intent)
                Actions.ACTION_REMOVE_ALL -> handleActionAll()
            }
        }
    }

    private fun handleActionAll() {
        Log.d(TAG, "handleActionAll")
        when (PhoneTypeManager.PHONE_TYPE) {
            PhoneTypeManager.TYPE_SUMSUNG -> Util.sendToSamSungAllContentResolver(applicationContext, 0)
            PhoneTypeManager.TYPE_HTC -> Util.sendToHtcAll(applicationContext, 0)
            PhoneTypeManager.TYPE_SONY -> Util.sendToSonyAll(applicationContext, 0)
        }

    }
//
//    private fun handleActionSamSung(intent: Intent) {
//        val packageName = intent.getStringExtra("badge_count_package_name")
//        val className = intent.getStringExtra("badge_count_class_name")
//        Util.sendToSamSumg(application, packageName, className, 0)
//    }
//
//    private fun handleActionHtc(intent: Intent) {
//        val packageName = intent.getStringExtra("packagename")
//        val className = intent.getStringExtra("badge_count_class_name")
//        Util.sendToHtc(application, packageName, 0)
//    }
//
//    private fun handleActionSony(intent: Intent) {
//        val packageName = intent.getStringExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME")
//        val className = intent.getStringExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME")
//        Util.sendToSony(application, packageName, className, 0)
//    }

    companion object {
        private val TAG = "RemoveBadgeService"

//        fun startRemoveOneApp(context: Context, i: Intent) {
//            i.setClass(context, RemoveBadgeService::class.java)
//            context.startService(i)
//        }

        fun startRemoveAll(context: Context) {
            val intent = Intent(context, RemoveBadgeService::class.java)
            intent.action = Actions.ACTION_REMOVE_ALL
            context.startService(intent)
        }
    }

}
