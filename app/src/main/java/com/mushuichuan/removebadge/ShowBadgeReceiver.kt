package com.mushuichuan.removebadge

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ShowBadgeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (Actions.ACTION_SUMSUNG == intent.action) {
            RemoveBadgeService.startRemoveSamSung(context, intent)
        }
    }
}