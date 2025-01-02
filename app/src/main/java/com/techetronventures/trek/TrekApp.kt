package com.techetronventures.trek

import android.app.Application
import android.os.StrictMode
import leakcanary.LeakCanary

class TrekApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){
            //Run Below Configuration only on debug mode
            //<editor-fold desc = "StrictMode To Detect Thread Policy Violation">
            /*
            * Detecting and fixing ThreadPolicy violations (like network or disk I/O
            * on the main thread) is critical for a smooth Ul.StrictMode helps catch
            * these issues early during development. It can report the issue through
            * a number of channels i-e logcat output, displaying a notification or crashing the app.
            */
            val policy = StrictMode.ThreadPolicy.Builder()  // Enable StrictMode for detecting Thread Policy Violations
                .detectAll() // Detect All Policy Violations
                .penaltyLog() // Report Violation to Logcat
                .penaltyDialog() // Flash the Screen When a Violation Occurs
                .build()
            StrictMode.setThreadPolicy(policy)
            //</editor-fold>

            //<editor-fold desc = "Leak Canary to Detect Memory Leak">
            LeakCanary.config = LeakCanary.config.copy(
                dumpHeap = true
            )
            //</editor-fold>
        }

    }
}