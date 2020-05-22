package com.example.annoyingex

import android.app.Application
import android.widget.Toast

class AnnoyingExApp: Application() {
    private var messages: MutableList<String>? = null

    lateinit var httpMessageManager: HttpMessageManager
        private set

    lateinit var exWorkManager: ExWorkManager
        private set

    lateinit var exNotificationManager: ExNotificationManager
        private set

    override fun onCreate() {
        super.onCreate()
        httpMessageManager = HttpMessageManager(this)

        fetchExMessages()

        exWorkManager = ExWorkManager(this)
        exNotificationManager = ExNotificationManager(this)
    }

    fun getRandomMessage(): String {
        messages?.let {
            return it.random()
        } ?: run {
            return "unable to retrieve message"
        }
    }

    private fun fetchExMessages() {
        httpMessageManager.getExMessages (
            { exMessages ->
                messages = exMessages.messages.toMutableList()
            },
            {
                toastMessage("Error! Something went wrong!")
            }
        )
    }

    private fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}