package com.infoapp

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Logger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class InfoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        FirebaseDatabase.getInstance().setLogLevel(Logger.Level.DEBUG)  // FirebaseDatabase.getInstance().setPersistenceEnabled(false)
    }
}