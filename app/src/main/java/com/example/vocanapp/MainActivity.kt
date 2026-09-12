package com.example.vocanapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.vocanapp.ui.theme.VocanAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val notificationDestination =
            intent.getStringExtra("destination")

        enableEdgeToEdge()
        setContent {
            VocanAppTheme {
                VocanApp(notificationDestination = notificationDestination)
            }
        }
    }

}