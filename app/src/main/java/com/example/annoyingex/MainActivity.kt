package com.example.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var httpMessageManager: HttpMessageManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val exApp = (application as AnnoyingExApp)
        httpMessageManager = exApp.httpMessageManager

        val exWorkManager = exApp.exWorkManager

        start_button.setOnClickListener {
            exWorkManager.startMessaging()
        }

        block_button.setOnClickListener {
            exWorkManager.stopWork()
        }
    }
}
