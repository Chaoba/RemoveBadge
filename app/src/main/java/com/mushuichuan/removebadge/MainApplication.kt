package com.mushuichuan.removebadge

import android.app.Application

/**
 * Created by Liyanshun on 2016/5/4.
 */
class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        PhoneTypeManager.getCurrentPhoneType()
    }
}