package com.example.vocanapp.data.local

import android.util.Log
import com.example.vocanapp.data.model.login.RefreshTokenRequest
import com.example.vocanapp.data.remote.AuthApi
import com.example.vocanapp.data.session.SessionManager
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val tokenStorage: TokenStorage,
    private val authApi: AuthApi,
    private val sessionManager: SessionManager
) : Authenticator {

    private val refreshMutex = Mutex()

    override fun authenticate(
        route: Route?,
        response: Response
    ): Request? {

        if (responseCount(response) >= 2) {
            return null
        }

        return runBlocking {
            refreshMutex.withLock {

                val refreshToken =
                    tokenStorage.getRefreshToken()
                        ?: return@withLock null

                try {

                    val tokenResponse =
                        authApi.refreshToken(
                            RefreshTokenRequest(
                                refreshToken = refreshToken
                            )
                        )

                    tokenStorage.saveToken(
                        token = tokenResponse.accessToken,
                        refreshToken = tokenResponse.refreshToken
                    )

                    response.request
                        .newBuilder()
                        .header(
                            "Authorization",
                            "Bearer ${tokenResponse.accessToken}"
                        )
                        .build()

                } catch (e: Exception) {
                    Log.e(
                        "SESSION EXPIRE REFRESH TOKEN",
                        "Error refreshing token",
                        e
                    )
                    sessionManager.logout()

                    null
                }
            }
        }
    }

    private fun responseCount(response: Response): Int {

        var count = 1
        var current = response

        while (current.priorResponse != null) {
            count++
            current = current.priorResponse!!
        }

        return count
    }
}