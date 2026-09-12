package com.example.vocanapp.data.remote

import com.example.vocanapp.data.local.TokenStorage
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenStorage: TokenStorage
) : Interceptor {

    override fun intercept(
        chain: Interceptor.Chain
    ): Response {

        val request = chain.request()

        val token = runBlocking {
            tokenStorage.getToken()
        }

        val authenticatedRequest = request
            .newBuilder()
            .apply {
                if (token != null) {
                    header(
                        "Authorization",
                        "Bearer $token"
                    )
                }
            }
            .build()

        return chain.proceed(authenticatedRequest)
    }
}