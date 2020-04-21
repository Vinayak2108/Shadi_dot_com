package com.shaadi.codechallenge.app

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco

class Shaadi:Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        Fresco.initialize(this)
    }

    companion object {
        lateinit  var appContext: Context
    }

}