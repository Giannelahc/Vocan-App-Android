package com.example.vocanapp.data.model.vocabulary

data class ConjugationResponse(
    val future: Map<String, String>? = null,
    val present: Map<String, String>? = null,
    val pastParticiple: String? = null
)