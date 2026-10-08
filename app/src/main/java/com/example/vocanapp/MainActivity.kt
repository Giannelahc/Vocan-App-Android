package com.example.vocanapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import coil.imageLoader
import coil.request.ImageRequest
import com.example.vocanapp.ui.theme.VocanAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val notificationDestination =
            intent.getStringExtra("destination")

        enableEdgeToEdge()

        val imageLoader = applicationContext.imageLoader

        imageLoader.enqueue(
            ImageRequest.Builder(applicationContext)
                .data(R.drawable.letters_loading)
                .build()
        )

        setContent {
            VocanAppTheme {
                VocanApp(notificationDestination = notificationDestination)
            }
        }
    }

}