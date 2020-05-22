package com.example.annoyingex

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class ExMessageWorker(context: Context, workParams: WorkerParameters): Worker(context, workParams) {
    private val myContext = context

    override fun doWork(): Result {
        val exApp = myContext.applicationContext as AnnoyingExApp
        exApp.exNotificationManager.showMessage(exApp.getRandomMessage())
        return Result.success()
    }
}