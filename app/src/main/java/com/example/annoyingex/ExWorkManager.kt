package com.example.annoyingex

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class ExWorkManager(context: Context) {

    private var workManager = WorkManager.getInstance(context)

    fun startMessaging() {
        if (isExRunning()) {
            stopWork()
        }

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<ExMessageWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .addTag(EX_WORK_REQUEST_TAG)
            .build()

        workManager.enqueue(workRequest)
    }

    private fun isExRunning(): Boolean {
        return when (workManager.getWorkInfosByTag(EX_WORK_REQUEST_TAG).get().firstOrNull()?.state) {
            WorkInfo.State.RUNNING,
            WorkInfo.State.ENQUEUED -> true
            else -> false
        }
    }

    fun stopWork() {
        workManager.cancelAllWorkByTag(EX_WORK_REQUEST_TAG)
    }

    companion object {
        const val EX_WORK_REQUEST_TAG = "EX_WORK_REQUEST_TAG"
    }
}