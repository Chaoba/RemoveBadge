package com.mushuichuan.removebadge

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ShowBadgeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (Actions.ACTION_SUMSUNG == intent.action) {
            val number = intent.getIntExtra("badge_count", 0)
            val isTest = intent.getBooleanExtra("isTest", false)
            // while we are testing, we will not remove badges
            if (!isTest && number > 0) {
                RemoveBadgeService.startRemoveSamSung(context, intent)
            }
        }
    }
}
