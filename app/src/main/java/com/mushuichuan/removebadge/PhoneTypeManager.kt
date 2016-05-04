package com.mushuichuan.removebadge

import android.os.Build
import android.util.Log
import java.util.*

/**
 * Created by Liyanshun on 2016/5/4.
 */
object PhoneTypeManager {
    private val TAG = "PhoneTypeManager"

    val TYPE_SUMSUNG = "sumsung"
    val TYPE_SONY = "sony"
    val TYPE_HTC = "htc"
    val TYPE_XIAOMI = "xiaomi"
    val TYPE_LG = "lg"
    val TYPE_NOT_SUPPORT = "not_support"

    var PHONE_TYPE = TYPE_SUMSUNG


    fun getCurrentPhoneType() {
        val manufacturer:String = Build.MANUFACTURER.toLowerCase(Locale.ENGLISH)
        Log.d(TAG, "manufacturer:$manufacturer")
        when {
            manufacturer.contains(TYPE_SUMSUNG) -> PHONE_TYPE = TYPE_SUMSUNG
            manufacturer.contains(TYPE_SONY) -> PHONE_TYPE = TYPE_SONY
            manufacturer.contains(TYPE_HTC) -> PHONE_TYPE = TYPE_HTC
            manufacturer.contains(TYPE_XIAOMI) -> PHONE_TYPE = TYPE_XIAOMI
            manufacturer.contains(TYPE_LG) -> PHONE_TYPE = TYPE_LG
            else -> PHONE_TYPE = TYPE_NOT_SUPPORT
        }
    }
}