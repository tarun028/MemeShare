package com.example.memeshare

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var meme: ImageView
    private var currentImageUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        meme = findViewById(R.id.meme)
        loadMeme()
    }

    private fun loadMeme() {
        progressBar.visibility = View.VISIBLE
        val url = "https://meme-api.com/gimme" // Replace with the actual URL of your meme image
        //Glide.with(this).clear(meme)
        // Create a Volley RequestQueue
        val queue = Volley.newRequestQueue(this)

        // Create a JsonObjectRequest to fetch the meme data
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->

                val imageUrl = response.getString("url")
                currentImageUrl = imageUrl // Store the current image URL

                Glide.with(this)
                    .load(imageUrl)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable,
                            model: Any,
                            target: Target<Drawable>?,
                            dataSource: DataSource,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.visibility = View.GONE
                            return false
                        }
                    })
                    .into(meme)
            },
            { error ->
                Toast.makeText(this, "Failed to load meme", Toast.LENGTH_LONG).show()
            }
        )

        // Add the request to the RequestQueue
        queue.add(jsonObjectRequest)
    }

    fun shareMeme(view: View) {
        // Implement sharing functionality here using currentImageUrl
        val intent = Intent(Intent.ACTION_SEND)
        intent.type= "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"Check this out $currentImageUrl")
        val chooser = Intent.createChooser(intent,"Share")
        startActivity(chooser)
    }

    fun nextMeme(view: View) {
        // Call the loadMeme function to load the next meme
        loadMeme()
    }
}


