package com.mushuichuan.removebadge

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ShowBadgeReceiver : BroadcastReceiver() {

    private val TAG = "ShowBadgeReceiver"

    override fun onReceive(context: Context, intent: Intent) {
        var number = 0
        when (intent.action) {
            Actions.ACTION_SUMSUNG -> number = intent.getIntExtra("badge_count", 0)
            Actions.ACTION_HTC -> number = intent.getIntExtra("count", 0)
            Actions.ACTION_SONY -> number = intent.getIntExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", 0)
        }
        Log.d(TAG, "onReceive number:$number")
        //if number <=0, no need to start service
        if (number > 0) {
//            RemoveBadgeService.startRemoveOneApp(context, intent)
        }
    }
}
