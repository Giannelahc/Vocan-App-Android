package com.example.vocanapp.data.model.user


data class HomeInfoResponse (
    val id: Int,
    val name: String,
    val lastname: String,
    val username: String,
    val languageStatistics: List<LanguageStatisticsResponse>,
    val streak: Int,
    val todayReviews: Int,
    val flashcardsToReview: Int,
    val flashcardsActive: Boolean

)