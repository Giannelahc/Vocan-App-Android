package com.example.vocanapp.data.mapper

import com.example.vocanapp.data.model.vocabulary.ConjugationResponse
import com.example.vocanapp.data.model.vocabulary.ExampleResponse
import com.example.vocanapp.data.model.vocabulary.LanguageResponse
import com.example.vocanapp.data.model.vocabulary.SynonymResponse
import com.example.vocanapp.data.model.vocabulary.UserExampleResponse
import com.example.vocanapp.data.model.vocabulary.VocabularyListResponse
import com.example.vocanapp.data.model.vocabulary.VocabularyWordResponse
import com.example.vocanapp.data.model.vocabulary.VocabularyWordSummaryResponse
import com.example.vocanapp.data.model.vocabulary.WordSenseResponse
import com.example.vocanapp.domain.model.Conjugation
import com.example.vocanapp.domain.model.Example
import com.example.vocanapp.domain.model.Language
import com.example.vocanapp.domain.model.Synonym
import com.example.vocanapp.domain.model.UserExample
import com.example.vocanapp.domain.model.Word
import com.example.vocanapp.domain.model.WordList
import com.example.vocanapp.domain.model.WordSense
import com.example.vocanapp.domain.model.WordSummary

fun VocabularyListResponse.toDomain(): WordList =
    WordList(
        page = page,
        pageSize = pageSize,
        total = total,
        items = items.map { it.toDomain() }
    )

fun VocabularyWordSummaryResponse.toDomain(): WordSummary =
    WordSummary(
        id = id,
        word = word,
        language = language?.toDomain(),
        status = status,
        senses = senses
    )

fun VocabularyWordResponse.toDomain(): Word =
    Word(
        id = id,
        word = word,
        language = language.toDomain(),
        status = status,
        senses = senses.map { it.toDomain() }
    )

fun LanguageResponse.toDomain(): Language =
    Language(
        id = id,
        code = code,
        name = name
    )

fun WordSenseResponse.toDomain(): WordSense =
    WordSense(
        id = id,
        grammarType = grammarType,
        definition = definition,
        translations = translations,
        conjugation = conjugation?.toDomain(),
        gender = gender,
        examples = examples.map { it.toDomain() },
        synonyms = synonyms.map { it.toDomain() },
        userExamples = userExamples?.map { it.toDomain() }
    )

fun ConjugationResponse.toDomain(): Conjugation =
    Conjugation(
        future = future.orEmpty(),
        present = present.orEmpty(),
        pastParticiple = pastParticiple?: ""
    )

fun ExampleResponse.toDomain(): Example =
    Example(
        id = id,
        sentence = sentence
    )

fun UserExampleResponse.toDomain(): UserExample =
    UserExample(
        id = id,
        sentence = sentence
    )

fun SynonymResponse.toDomain(): Synonym =
    Synonym(
        id = id,
        word = word
    )