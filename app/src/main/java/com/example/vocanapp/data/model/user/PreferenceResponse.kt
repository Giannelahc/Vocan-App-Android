package com.example.vocanapp.data.model.user

import com.google.gson.annotations.SerializedName


data class PreferenceResponse (
    val id: Int,
    @SerializedName("native_language")
    val nativeLanguage: LanguageResponse,
    @SerializedName("learning_languages")
    val learningLanguages: List<LanguageResponse>
)