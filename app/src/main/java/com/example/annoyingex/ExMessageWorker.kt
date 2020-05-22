package com.example.annoyingex

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class ExMessageWorker(private val context: Context, workParams: WorkerParameters): Worker(context, workParams) {
    override fun doWork(): Result {
        Log.i("nilsen", "Hey, this works!")

        return Result.success()
    }
}