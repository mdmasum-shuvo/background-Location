package com.example.backgroundlocation.workmanager


import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object LocationInBackGround {

    @RequiresApi(Build.VERSION_CODES.N)
    @Synchronized
    fun startWorkManager(context: Context) {

        val constraints =
            Constraints.Builder().setTriggerContentMaxDelay(1, TimeUnit.SECONDS).build()
        val periodicWorkRequest =
            PeriodicWorkRequest.Builder(BackgroundLocationTracker::class.java, 30, TimeUnit.MINUTES)
                .setConstraints(constraints).build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "location",
            ExistingPeriodicWorkPolicy.REPLACE,
            periodicWorkRequest
        )
//        WorkManager.getInstance().enqueue(periodicWorkRequest)

        Log.d("testCheck", "startWorkManager")
    }



}