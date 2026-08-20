package com.example.vocanapp.data.model.user

import com.google.gson.annotations.SerializedName
import java.util.Date


data class UserInfoResponse (
    val id: Int,
    val name: String,
    val lastname: String,
    val username: String,
    val email: String,
    @SerializedName("created_at")
    val createdAt: Date,
    val preference: PreferenceResponse

)