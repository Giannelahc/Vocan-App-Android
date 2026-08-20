package com.example.vocanapp.data.remote

import com.example.vocanapp.data.local.TokenStorage
import com.example.vocanapp.data.session.SessionManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenStorage: TokenStorage,
    private val sessionManager: SessionManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (
            request.url.encodedPath.endsWith("/auth/login") ||
            request.url.encodedPath.endsWith("/auth/register")
        ) {
            return chain.proceed(request)
        }

        val token = runBlocking {
            tokenStorage.getToken()
        }

        val authenticatedRequest = request
            .newBuilder()
            .apply {
                if (token != null) {
                    addHeader(
                        "Authorization",
                        "Bearer $token"
                    )
                }
            }
            .build()

        val response = chain.proceed(authenticatedRequest)

        if (response.code == 401 &&
            !chain.request().url.encodedPath.contains("/auth/login")) {

            runBlocking {
                sessionManager.logout()
            }
        }

        return response
    }
}