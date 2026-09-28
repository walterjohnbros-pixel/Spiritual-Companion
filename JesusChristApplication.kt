package com.example

import android.app.Application
import android.util.Log
import com.google.android.gms.ads.MobileAds

class JesusChristApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        // Wrap background initialization in try-catch to prevent startup crash
        try {
            MobileAds.initialize(this) { status ->
                Log.d("JesusChristApp", "AdMob initialized successfully")
            }
        } catch (e: Exception) {
            Log.e("JesusChristApp", "Failed to initialize AdMob safely: ${e.message}")
        }
    }
}
