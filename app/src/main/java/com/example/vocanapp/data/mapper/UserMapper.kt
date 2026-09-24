package com.example.vocanapp.data.mapper

import com.example.vocanapp.data.model.user.HomeInfoResponse
import com.example.vocanapp.data.model.user.LanguageResponse
import com.example.vocanapp.data.model.user.LanguageStatisticsResponse
import com.example.vocanapp.data.model.user.UserInfoResponse
import com.example.vocanapp.domain.model.HomeInfo
import com.example.vocanapp.domain.model.Language
import com.example.vocanapp.domain.model.LanguageStatistics
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

fun LanguageStatisticsResponse.toDomain(): LanguageStatistics =
    LanguageStatistics(
        languageId = languageId,
        languageName = languageName,
        totalWords = totalWords,
        newWordsCurrentWeek = newWordsCurrentWeek,
        learnedWords = learnedWords
    )

fun HomeInfoResponse.toDomain(): HomeInfo =
    HomeInfo(
        id = id,
        name = name,
        lastname = lastname,
        username = username,
        languageStatistics = languageStatistics.map { it.toDomain() },
        streak = streak,
        todayReviews = todayReviews,
        flashcardsToReview = flashcardsToReview,
        flashcardsActive = flashcardsActive
    )