package com.example.vocanapp

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import com.example.vocanapp.data.notification.ReviewNotificationScheduler
import com.example.vocanapp.data.session.SessionInitializer
import com.example.vocanapp.data.session.SessionManager
import com.example.vocanapp.presentation.session.SessionState
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class VocanApplication : Application(),
    Configuration.Provider, ImageLoaderFactory {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var sessionManager: SessionManager

    @Inject
    lateinit var sessionInitializer: SessionInitializer

    @Inject
    lateinit var reviewNotificationScheduler: ReviewNotificationScheduler

    private val applicationScope =
        CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        initializeSession()
    }

    private fun initializeSession() {
        applicationScope.launch {

            launch {
                sessionManager.sessionState
                    .collect { state ->
                        when (state) {
                            SessionState.LoggedIn -> {
                                reviewNotificationScheduler.schedule()
                            }

                            SessionState.LoggedOut -> {
                                //workManager.cancelUniqueWork(WORK_NAME)
                            }

                            SessionState.Loading -> Unit
                        }
                    }
            }

            sessionInitializer.initialize()
        }
    }

    override val workManagerConfiguration:
            Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                add(GifDecoder.Factory())
            }
            .build()
    }
}