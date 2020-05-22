package com.example.annoyingex

import android.app.Application

class AnnoyingExApp: Application() {
    lateinit var httpMessageManager: HttpMessageManager

    lateinit var exWorkManager: ExWorkManager
        private set

    override fun onCreate() {
        super.onCreate()
        httpMessageManager = HttpMessageManager(this)
        exWorkManager = ExWorkManager(this)
    }
}