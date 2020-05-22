package com.example.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var httpMessageManager: HttpMessageManager
    private var messages: MutableList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val exApp = (application as AnnoyingExApp)
        httpMessageManager = exApp.httpMessageManager

        val exWorkManager = exApp.exWorkManager

        fetchExMessages()

        start_button.setOnClickListener {
            exWorkManager.startMessaging()
        }

        block_button.setOnClickListener {
            exWorkManager.stopWork()
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

    private fun getRandomMessage(): String {
        messages?.let {
            return it.random()
        } ?: run {
            return "unable to retrieve message"
        }
    }
}
