package com.example.vocanapp.presentation.main.vocabulary.wordidentification

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocanapp.domain.model.Language
import com.example.vocanapp.domain.usecase.AddSelectedWordsUseCase
import com.example.vocanapp.domain.usecase.GetLanguagesListUseCase
import com.example.vocanapp.domain.usecase.IdentifyWordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IdentifyWordsViewModel @Inject constructor(
    private val identifyWordsUseCase: IdentifyWordsUseCase,
    private val getLanguagesListUseCase: GetLanguagesListUseCase,
    private val addSelectedWordsUseCase: AddSelectedWordsUseCase
) : ViewModel() {

    var uiState by mutableStateOf(IdentifyWordsUiState())
        private set

    private val _events = MutableSharedFlow<WordIdentificationEvent>()

    val events = _events.asSharedFlow()

    init {
        loadLanguages()
    }

    fun onSentenceChanged(sentence: String) {
        uiState = uiState.copy(
            sentence = sentence,
            error = null
        )
    }

    private fun loadLanguages() {

        viewModelScope.launch {

            try {

                val languages = getLanguagesListUseCase()

                uiState = uiState.copy(
                    languages = languages
                )

            } catch (e: Exception) {

                Log.e(
                    "LOAD_LANGUAGES_ERROR",
                    "Failed to load languages",
                    e
                )
            }
        }
    }

    fun onLanguageSelected(language: Language?) {
        uiState = uiState.copy(
            language = language
        )
    }

    fun identify() {

        if (uiState.sentence.isBlank()) {
            return
        }

        viewModelScope.launch {

            uiState = uiState.copy(
                isLoading = true,
                error = null
            )

            try {

                val options = identifyWordsUseCase(
                    uiState.sentence,
                    uiState.language?.id ?: 1
                )

                uiState = uiState.copy(
                    isLoading = false,
                    options = options
                )

            } catch (e: Exception) {

                uiState = uiState.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun addSelectedWords() {

        val language = uiState.language ?: return

        val selectedWords = uiState.options
            .filter { it.baseForm in uiState.selectedWordIds }

        if (selectedWords.isEmpty()) {
            return
        }

        viewModelScope.launch {

            uiState = uiState.copy(
                isAdding = true,
                error = null
            )

            try {

                addSelectedWordsUseCase(
                    words = selectedWords.map { it.baseForm },
                    languageId = language.id
                )

                uiState = uiState.copy(
                    isAdding = false
                )

                _events.emit(
                    WordIdentificationEvent.WordsAdded
                )

            } catch (e: Exception) {

                Log.e(
                    "ADD_SELECTED_WORDS_ERROR",
                    "Failed to add selected words",
                    e
                )

                uiState = uiState.copy(
                    isAdding = false,
                    error = e.message
                )
            }
        }
    }

    fun toggleWordSelection(wordId: String) {

        val selected = uiState.selectedWordIds.toMutableSet()

        if (selected.contains(wordId)) {
            selected.remove(wordId)
        } else {
            selected.add(wordId)
        }

        uiState = uiState.copy(
            selectedWordIds = selected
        )
    }
}