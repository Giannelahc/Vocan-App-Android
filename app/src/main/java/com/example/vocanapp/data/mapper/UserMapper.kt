package com.example.vocanapp.data.mapper

import com.example.vocanapp.data.model.user.LanguageResponse
import com.example.vocanapp.data.model.user.PreferenceResponse
import com.example.vocanapp.data.model.user.UserInfoResponse
import com.example.vocanapp.domain.model.Language
import com.example.vocanapp.domain.model.Preference
import com.example.vocanapp.domain.model.UserInfo

fun UserInfoResponse.toDomain(): UserInfo =
    UserInfo(
        id = id,
        name = name,
        lastname = lastname,
        username = username,
        email= email,
        createdAt = createdAt,
        preference = preference.toDomain()
    )

fun PreferenceResponse.toDomain(): Preference =
    Preference(
        id = id,
        nativeLanguage = nativeLanguage.toDomain(),
        learningLanguages = learningLanguages.map { it.toDomain() }
    )

fun LanguageResponse.toDomain(): Language =
    Language(
        id = id,
        code = code,
        name = name
    )