package com.mushuichuan.removebadge;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Liyanshun on 2016/4/25.
 */
public class Util {

    private static final String TAG = "Util";

    public static void sendToSamsumg(Context context, String packageName, String className,int number) {
        Log.d(TAG, "packageName:" + packageName);
        Log.d(TAG, "className:" + className);
        Intent localIntent = new Intent(Actions.ACTION_SUMSUNG);
        localIntent.putExtra("badge_count", number);
        localIntent.putExtra("badge_count_package_name", packageName);
        localIntent.putExtra("badge_count_class_name", className);
        context.sendBroadcast(localIntent);
    }
}
