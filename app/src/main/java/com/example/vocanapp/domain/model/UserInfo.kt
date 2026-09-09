package com.example.vocanapp.domain.model

import java.util.Date

data class UserInfo (
    val id: Int? = null,
    val name: String,
    val lastname: String,
    val username: String,
    val email: String,
    val createdAt: Date,
    val preference: Preference?
)