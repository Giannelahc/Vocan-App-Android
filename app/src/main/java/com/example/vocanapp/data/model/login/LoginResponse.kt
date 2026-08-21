package com.example.vocanapp.data.model.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val accessToken: String,

    val tokenType: String
)