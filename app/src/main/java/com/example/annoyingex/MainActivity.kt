package com.example.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var httpMessageManager: HttpMessageManager
    lateinit var messages: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        httpMessageManager = (application as AnnoyingExApp).httpMessageManager

        fetchExMessages()

        start_button.setOnClickListener {
            toastMessage(messages.toString())
        }
    }


    private fun fetchExMessages() {
        httpMessageManager.getExMessages (
            { exMessages ->
                messages = exMessages.messages
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
