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
            if (Actions.ACTION_SUMSUNG == action) {
                handleActionSamsung(intent)
            }
        }
    }

    private fun handleActionSamsung(intent: Intent) {
        val number = intent.getIntExtra("badge_count", 0)
        if (number > 0) {
            val packageName = intent.getStringExtra("badge_count_package_name")
            val className = intent.getStringExtra("badge_count_class_name")
            Util.sendToSamsumg(application, packageName, className, 0)

        }
    }

    companion object {
        private val TAG = "RemoveBadgeService"


        fun startRemoveSamSung(context: Context, i: Intent) {
            val intent = Intent(context, RemoveBadgeService::class.java)
            intent.action = Actions.ACTION_SUMSUNG
            intent.putExtras(i)
            context.startService(intent)
        }
    }

}
