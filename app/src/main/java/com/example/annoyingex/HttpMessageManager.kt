package com.example.annoyingex

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class HttpMessageManager(context: Context) {

    private val queue: RequestQueue = Volley.newRequestQueue(context)

    fun getExMessages(onMessagesReady: (ExMessages) -> Unit, onError: (() -> Unit)? = null) {
        val userURL = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json"

        val request = StringRequest(
            Request.Method.GET, userURL,
            { response ->
                // Success
                val gson = Gson()
                val exMessages = gson.fromJson(response, ExMessages::class.java )

                onMessagesReady(exMessages)

            },
            {
                // Failure
                onError?.invoke()
            }
        )

        queue.add(request)
    }
}