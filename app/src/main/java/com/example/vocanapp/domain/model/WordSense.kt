package com.example.vocanapp.domain.model


data class WordSense(
    val id: Int,
    val grammarType: String,
    val definition: String,
    val translations: Map<String, List<String>>,
    val conjugation: Conjugation?,
    val gender: String?,
    val examples: List<Example>,
    val synonyms: List<Synonym>,
    val userExamples: List<UserExample>?
)
