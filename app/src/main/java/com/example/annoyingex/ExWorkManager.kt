package com.example.annoyingex

import android.content.Context
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class ExWorkManager(context: Context) {

    private var workManager = WorkManager.getInstance(context)

    fun startMessaging() {
        if (isExRunning()) {
            stopWork()
        }

        /*
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
        */

        // TODO: Set request type to be periodic work
        val workRequest = OneTimeWorkRequestBuilder<ExMessageWorker>()
            .setInitialDelay(5000, TimeUnit.MILLISECONDS)
            //.setConstraints(constraints)
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