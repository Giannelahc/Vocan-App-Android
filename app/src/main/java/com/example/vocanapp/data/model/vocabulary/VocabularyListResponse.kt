package com.example.vocanapp.data.model.vocabulary


data class VocabularyListResponse(
    val items: List<VocabularyWordSummaryResponse>,
    val page: Int,
    val pageSize: Int,
    val total: Int
)