package com.example.vocanapp.presentation.main.vocabulary

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Log
import com.example.vocanapp.domain.model.Language
import com.example.vocanapp.domain.usecase.AddWordUseCase
import com.example.vocanapp.domain.usecase.GetLanguagesListUseCase
import com.example.vocanapp.domain.usecase.GetWordsListUseCase
import com.example.vocanapp.presentation.main.vocabulary.components.addWord.AddWordUiState
import com.example.vocanapp.presentation.main.vocabulary.components.addWord.VocabularyEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow


@HiltViewModel
class VocabularyViewModel @Inject constructor(
    private val getWordsListUseCase: GetWordsListUseCase,
    private val getLanguagesListUseCase: GetLanguagesListUseCase,
    private val addWordUseCase: AddWordUseCase,
) : ViewModel() {

    var uiState by mutableStateOf(VocabularyUiState())
        private set

    var addWordUiState by mutableStateOf(AddWordUiState())
        private set

    private val _events = MutableSharedFlow<VocabularyEvent>()

    val events = _events.asSharedFlow()

    private var searchJob: Job? = null

    init {
        loadInitialData()
    }

    private fun loadInitialData() {

        loadLanguages()
        loadInitialWords()
    }

    fun onWordChanged(word: String) {

        addWordUiState = addWordUiState.copy(
            word = word,
            error = null
        )
    }

    fun onAddLanguageSelected(language: Language?) {

        addWordUiState = addWordUiState.copy(
            selectedLanguage = language,
            error = null
        )
    }

    fun addWord() {

        val word = addWordUiState.word.trim()
        val language = addWordUiState.selectedLanguage

        if (word.isBlank() || language == null) {
            return
        }

        viewModelScope.launch {

            addWordUiState = addWordUiState.copy(
                isLoading = true,
                error = null
            )

            try {

                addWordUseCase(
                    word = word,
                    languageId = language.id
                )

                addWordUiState = addWordUiState.copy(
                    isLoading = false
                )

                _events.emit(
                    VocabularyEvent.WordAdded
                )

            } catch (e: Exception) {

                Log.e(
                    "ADD_WORD_ERROR",
                    "Failed to add word",
                    e
                )

                addWordUiState = addWordUiState.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun resetAddWordState() {

        addWordUiState = AddWordUiState()
    }

    fun refreshWords() {

        viewModelScope.launch {

            loadWords(
                page = 1,
                language = uiState.selectedLanguage,
                search = uiState.searchQuery,
                isInitialLoad = true
            )
        }
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

    private fun loadInitialWords() {

        viewModelScope.launch {

            loadWords(
                page = 1,
                isInitialLoad = true
            )
        }
    }

    fun loadMore() {

        if (
            uiState.isLoading ||
            uiState.isLoadingMore ||
            !uiState.hasMore
        ) {
            return
        }

        viewModelScope.launch {

            loadWords(
                page = uiState.currentPage + 1,
                isInitialLoad = false
            )
        }
    }

    fun loadWordsByLanguage(
        language: Language?
    ) {

        searchJob?.cancel()

        uiState = uiState.copy(
            selectedLanguage = language,
            currentPage = 0,
            words = emptyList(),
            totalWords = 0,
            hasMore = true
        )

        viewModelScope.launch {

            loadWords(
                page = 1,
                language = language,
                search = uiState.searchQuery,
                isInitialLoad = true
            )
        }
    }

    fun search(query: String) {

        uiState = uiState.copy(
            searchQuery = query
        )

        searchJob?.cancel()

        searchJob = viewModelScope.launch {

            delay(400)

            uiState = uiState.copy(
                currentPage = 0,
                words = emptyList(),
                totalWords = 0,
                hasMore = true
            )

            loadWords(
                page = 1,
                language = uiState.selectedLanguage,
                search = query,
                isInitialLoad = true
            )
        }
    }

    private suspend fun loadWords(
        page: Int,
        language: Language? = uiState.selectedLanguage,
        search: String? = uiState.searchQuery,
        isInitialLoad: Boolean
    ) {

        uiState = uiState.copy(
            isLoading = isInitialLoad,
            isLoadingMore = !isInitialLoad,
            error = null
        )

        try {

            val response = getWordsListUseCase(
                languageId = language?.id,
                search = search?.takeIf { it.isNotBlank() },
                page = page,
                pageSize = uiState.pageSize
            )

            val words =
                if (isInitialLoad) {
                    response.items
                } else {
                    uiState.words + response.items
                }

            uiState = uiState.copy(
                isLoading = false,
                isLoadingMore = false,
                words = words,
                totalWords = response.total,
                currentPage = response.page,
                hasMore =
                    response.page * response.pageSize < response.total,
                refreshTrigger =
                    if (isInitialLoad) {
                        uiState.refreshTrigger + 1
                    } else {
                        uiState.refreshTrigger
                    }
            )

        } catch (e: Exception) {

            Log.e(
                "LOAD_WORDS_LIST_ERROR",
                "Failed to load words",
                e
            )

            uiState = uiState.copy(
                isLoading = false,
                isLoadingMore = false,
                error = e.message
            )
        }
    }

    override fun onCleared() {
        searchJob?.cancel()
        super.onCleared()
    }
}