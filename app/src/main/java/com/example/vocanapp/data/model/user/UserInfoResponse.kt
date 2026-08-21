package com.example.vocanapp.data.model.user

import java.util.Date


data class UserInfoResponse (
    val id: Int,
    val name: String,
    val lastname: String,
    val username: String,
    val email: String,
    val createdAt: Date,
    val preference: PreferenceResponse

)