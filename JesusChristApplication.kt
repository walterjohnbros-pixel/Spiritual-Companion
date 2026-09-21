package com.example

import android.app.Application
import android.util.Log
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.example.data.AppDatabase
import com.example.notification.PrayerReminderManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration

class JesusChristApplication : Application(), ImageLoaderFactory {

    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
    private var coilImageLoader: ImageLoader? = null

    override fun onCreate() {
        super.onCreate()
        try {
            cleanupStaleWebViewSeed()

            val configuration = RequestConfiguration.Builder()
                .setTestDeviceIds(listOf(AdRequest.DEVICE_ID_EMULATOR))
                .build()
            MobileAds.setRequestConfiguration(configuration)

            Thread {
                try {
                    MobileAds.initialize(this) { status ->
                        Log.d("JesusChristApplication", "AdMob initialized: ${status.adapterStatusMap}")
                    }
                } catch (e: Exception) {
                    Log.w("JesusChristApplication", "AdMob init failure: ${e.message}")
                }
            }.start()
        } catch (e: Exception) {
            Log.w("JesusChristApplication", "AdMob startup error: ${e.message}")
        }

        PrayerReminderManager.createNotificationChannel(this)
    }

    private fun cleanupStaleWebViewSeed() {
        try {
            val dataDir = applicationInfo.dataDir?.let { java.io.File(it) } ?: filesDir?.parentFile
            if (dataDir != null && dataDir.exists()) {
                val webviewDir = java.io.File(dataDir, "app_webview")
                if (webviewDir.exists() && webviewDir.isDirectory) {
                    listOf("variations_seed", "variations_seed_new").forEach { seedName ->
                        val seedFile = java.io.File(webviewDir, seedName)
                        if (seedFile.exists()) seedFile.delete()
                    }
                }
            }
        } catch (e: Exception) {
            Log.d("JesusChristApplication", "WebView seed cleanup: ${e.message}")
        }
    }

    override fun newImageLoader(): ImageLoader {
        return coilImageLoader ?: synchronized(this) {
            coilImageLoader ?: ImageLoader.Builder(this)
                .memoryCache {
                    MemoryCache.Builder(this)
                        .maxSizePercent(0.25)
                        .build()
                }
                .diskCache {
                    DiskCache.Builder()
                        .directory(cacheDir.resolve("image_cache"))
                        .maxSizeBytes(50L * 1024 * 1024)
                        .build()
                }
                .respectCacheHeaders(false)
                .build().also { coilImageLoader = it }
        }
    }
}