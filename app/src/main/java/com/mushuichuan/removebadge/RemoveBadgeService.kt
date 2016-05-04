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
                Actions.ACTION_SUMSUNG -> handleActionSamSung(intent)
                Actions.ACTION_REMOVE_ALL -> handleActionSamSungAll()
            }
        }
    }

    private fun handleActionSamSungAll() {
        Log.d(TAG, "handleActionSamSungAll")
        Util.sendToSamSungAllContentResolver(applicationContext, 0)
    }

    private fun handleActionSamSung(intent: Intent) {
        val number = intent.getIntExtra("badge_count", 0)
        if (number > 0) {
            val packageName = intent.getStringExtra("badge_count_package_name")
            val className = intent.getStringExtra("badge_count_class_name")
            Util.sendToSamSumg(application, packageName, className, 0)
        }
    }

    companion object {
        private val TAG = "RemoveBadgeService"

        fun startRemoveOneApp(context: Context, i: Intent) {
            i.setClass(context, RemoveBadgeService::class.java)
            context.startService(i)
        }

        fun startRemoveAll(context: Context) {
            val intent = Intent(context, RemoveBadgeService::class.java)
            intent.action = Actions.ACTION_REMOVE_ALL
            context.startService(intent)
        }
    }

}
