package com.example.vocanapp.data.mapper

import com.example.vocanapp.data.model.preference.PreferenceRequest
import com.example.vocanapp.data.model.preference.PreferenceResponse
import com.example.vocanapp.domain.model.Preference

fun PreferenceResponse.toDomain(): Preference =
    Preference(
        id = id,
        nativeLanguage = nativeLanguage.toDomain(),
        learningLanguages = learningLanguages.map { it.toDomain() }
    )

fun Preference.toRequest(): PreferenceRequest =
    PreferenceRequest(
        id = id,
        nativeLanguageId = nativeLanguage.id,
        learningLanguagesIds = learningLanguages.map { it.id }
    )
