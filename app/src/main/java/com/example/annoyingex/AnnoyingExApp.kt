package com.example.annoyingex

import android.app.Application

class AnnoyingExApp: Application() {
    lateinit var httpMessageManager: HttpMessageManager

    override fun onCreate() {
        super.onCreate()
        httpMessageManager = HttpMessageManager(this)
    }
}