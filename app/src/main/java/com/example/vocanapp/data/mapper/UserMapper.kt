package com.example.vocanapp.data.mapper

import com.example.vocanapp.data.model.user.HomeInfoResponse
import com.example.vocanapp.data.model.user.LanguageResponse
import com.example.vocanapp.data.model.user.UserInfoResponse
import com.example.vocanapp.domain.model.HomeInfo
import com.example.vocanapp.domain.model.Language
import com.example.vocanapp.domain.model.UserInfo

fun UserInfoResponse.toDomain(): UserInfo =
    UserInfo(
        id = id,
        name = name,
        lastname = lastname,
        username = username,
        email= email,
        createdAt = createdAt,
        preference = preference?.toDomain()
    )

fun LanguageResponse.toDomain(): Language =
    Language(
        id = id,
        code = code,
        name = name
    )

fun HomeInfoResponse.toDomain(): HomeInfo =
    HomeInfo(
        id = id,
        name = name,
        lastname = lastname,
        username = username,
        newWordsCurrentWeek = newWordsCurrentWeek,
        learnedWords = learnedWords
    )