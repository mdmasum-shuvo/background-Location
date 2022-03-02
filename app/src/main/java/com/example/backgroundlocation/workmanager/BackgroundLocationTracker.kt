 package com.example.backgroundlocation.workmanager

 import android.Manifest

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location

import android.util.Log
import androidx.core.app.ActivityCompat

import androidx.work.Worker
import androidx.work.WorkerParameters

import com.google.android.gms.location.LocationServices

class BackgroundLocationTracker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams)
     {
    private val context = appContext

    private fun initComponent() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        LocationServices.getFusedLocationProviderClient(context)
            ?.lastLocation
            ?.addOnSuccessListener { location: Location? ->
                Log.e("location", "location called")
                if (location != null){
                    Log.e("location", "location called:"+location.longitude)
                    //setupNotification()

                }
            }


    }






    override fun doWork(): Result {
        Log.e("location", "working manager")



        initComponent()
        return Result.success()
    }
}