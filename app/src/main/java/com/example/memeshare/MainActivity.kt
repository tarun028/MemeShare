package com.example.memeshare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val meme: ImageView = findViewById(R.id.meme)
        val url = "https://meme-api.com/gimme" // Replace with the actual URL of your meme image

        // Create a Volley RequestQueue
        val queue = Volley.newRequestQueue(this)

        // Create a JsonObjectRequest to fetch the meme data
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->

                val url = response.getString("url")
                Glide.with(this)
                    .load(url)
                    .into(meme)
            },
            { error ->
                Toast.makeText(this,"Glat hai",Toast.LENGTH_LONG).show()
            }
        )

        // Add the request to the RequestQueue
        queue.add(jsonObjectRequest)
    }
}

    fun shareMeme(view: View) {

    }
    fun nextMeme(view: View) {

    }
