package com.example.vocanapp.data.model.preference

data class PreferenceRequest(
    val id: Int? = null,
    val nativeLanguageId: Int,
    val learningLanguagesIds: List<Int>
)