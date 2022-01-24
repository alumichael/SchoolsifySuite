package com.schoolsify.suite

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class SuiteApplication:Application() {
    override fun onCreate() {
        super.onCreate()


    }
}