package com.example.vocanapp.data.model.vocabulary


data class WordSenseResponse(
    val id: Int,
    val grammarType: String,
    val definition: String,
    val translations: Map<String, List<String>>,
    val conjugation: ConjugationResponse,
    val gender: String?,
    val examples: List<ExampleResponse>,
    val synonyms: List<SynonymResponse>,
    val userExamples: List<UserExampleResponse>?
)
