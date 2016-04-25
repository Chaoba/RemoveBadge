package com.mushuichuan.removebadge;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
public class RemoveBadgeService extends IntentService {
    private static final String TAG = "RemoveBadgeService";

    public RemoveBadgeService() {
        super("RemoveBadgeService");
    }


    public static void startRemoveSamSung(Context context, Intent i) {
        Intent intent = new Intent(context, RemoveBadgeService.class);
        intent.setAction(Actions.ACTION_SUMSUNG);
        intent.putExtras(i);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            Log.d(TAG, "Handle action:" + action);
            if (Actions.ACTION_SUMSUNG.equals(action)) {
                handleActionSamsung(intent);
            }
        }
    }

    private void handleActionSamsung(final Intent intent) {
        int number = intent.getIntExtra("badge_count", 0);
        if (number > 0) {
            String packageName = intent.getStringExtra("badge_count_package_name");
            String className = intent.getStringExtra("badge_count_class_name");
            Util.sendToSamsumg(getApplication(), packageName, className, 0);

        }
    }

}
