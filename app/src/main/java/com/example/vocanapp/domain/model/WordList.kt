package com.example.vocanapp.domain.model


data class WordList(
    val items: List<WordSummary>,
    val page: Int,
    val pageSize: Int,
    val total: Int
)