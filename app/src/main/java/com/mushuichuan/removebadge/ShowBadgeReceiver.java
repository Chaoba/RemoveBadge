package com.mushuichuan.removebadge;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ShowBadgeReceiver extends BroadcastReceiver {
    public ShowBadgeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Actions.ACTION_SUMSUNG.equals(intent.getAction())) {
            RemoveBadgeService.startRemoveSamSung(context, intent);
        }
    }
}
