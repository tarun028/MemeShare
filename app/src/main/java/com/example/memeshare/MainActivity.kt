package com.example.memeshare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMeme()
    }

    private fun loadMeme(){
        val queue = Volley.newRequestQueue(this)
        val url = "https://www.google.com"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                Log.d("success Request", response.substring(0, 500))
            },
            Response.ErrorListener {
                Log.d("error", it.localizedMessage)
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    fun shareMeme(view: View) {

    }
    fun nextMeme(view: View) {

    }
}