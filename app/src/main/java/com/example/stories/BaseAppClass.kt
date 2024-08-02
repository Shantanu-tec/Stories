package com.example.stories

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseAppClass : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
        singleton = this
    }

    companion object {
        var appContext: Context? = null

        private var singleton: BaseAppClass? = null

        val instance: BaseAppClass?
            get() {
                if (singleton == null) {
                    singleton = BaseAppClass()
                }
                return singleton
            }
    }
}