package com.example.vocanapp.presentation.main.vocabulary.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocanapp.domain.usecase.AddUserExamplesUseCase
import com.example.vocanapp.domain.usecase.DeleteWordUseCase
import com.example.vocanapp.domain.usecase.GetWordDetailUseCase
import com.example.vocanapp.domain.usecase.RegenerateWordUseCase
import com.example.vocanapp.presentation.main.vocabulary.detail.components.addUserExamples.AddUserExamplesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VocabularyDetailViewModel @Inject constructor(
    private val getWordUseCase: GetWordDetailUseCase,
    private val regenerateWordUseCase: RegenerateWordUseCase,
    private val deleteWordUseCase: DeleteWordUseCase,
    private val addUserExamplesUseCase: AddUserExamplesUseCase
) : ViewModel() {

    var uiState by mutableStateOf(
        VocabularyDetailUiState()
    )
        private set

    var addExamplesUiState by mutableStateOf(
        AddUserExamplesUiState()
    )
        private set

    private val _events =
        MutableSharedFlow<VocabularyDetailEvent>()

    val events =
        _events.asSharedFlow()

    fun loadWord(wordId: Int) {

        viewModelScope.launch {

            uiState = uiState.copy(
                isLoading = true,
                error = null
            )

            try {

                val word = getWordUseCase(wordId)

                uiState = uiState.copy(
                    isLoading = false,
                    word = word
                )

            } catch (e: Exception) {

                Log.e(
                    "LOAD WORD ERROR",
                    "Load word error",
                    e
                )

                uiState = uiState.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun regenerateContent() {

        viewModelScope.launch {

            try {

                val word = uiState.word
                    ?: return@launch

                regenerateWordUseCase(
                    word.word,
                    word.language.id
                )
                _events.emit(
                    VocabularyDetailEvent.ContentRegenerated
                )

            } catch (e: Exception) {
                Log.e(
                    "REGENERATE_WORD_ERROR",
                    "Regenerate word content error",
                    e
                )

                uiState = uiState.copy(
                    error = e.message
                )
            }
        }
    }

    fun deleteWord() {
        viewModelScope.launch {
            try {

                val word = uiState.word
                    ?: return@launch

                deleteWordUseCase(word.id)
                _events.emit(
                    VocabularyDetailEvent.WordDeleted
                )

            } catch (e: Exception) {
                Log.e(
                    "DELETE_WORD_ERROR",
                    "Delete word error",
                    e
                )

                uiState = uiState.copy(
                    error = e.message
                )
            }
        }

    }

    fun openAddExamples(senseId: Int) {

        uiState = uiState.copy(
            addExamplesSenseId = senseId
        )

        addExamplesUiState =
            AddUserExamplesUiState(
                examples = listOf("")
            )
    }

    fun addExampleField() {

        addExamplesUiState =
            addExamplesUiState.copy(
                examples =
                    addExamplesUiState.examples + ""
            )
    }

    fun saveExamples() {

        viewModelScope.launch {

            val senseId =
                uiState.addExamplesSenseId
                    ?: return@launch

            val examples =
                addExamplesUiState.examples
                    .map { it.trim() }
                    .filter { it.isNotBlank() }

            if (examples.isEmpty()) {
                return@launch
            }

            addExamplesUiState =
                addExamplesUiState.copy(
                    isSaving = true,
                    error = null
                )

            try {

                addUserExamplesUseCase(
                    wordSenseId = senseId,
                    examples = examples
                )

                loadWord(
                    uiState.word!!.id
                )

                closeAddExamples()

            } catch (e: Exception) {

                Log.e(
                    "ADD_USER_EXAMPLES_ERROR",
                    "Failed to add user examples",
                    e
                )

                addExamplesUiState =
                    addExamplesUiState.copy(
                        isSaving = false,
                        error = e.message
                    )
            }
        }
    }

    fun updateExample(
        index: Int,
        value: String
    ) {

        val examples =
            addExamplesUiState.examples.toMutableList()

        examples[index] = value

        addExamplesUiState =
            addExamplesUiState.copy(
                examples = examples
            )
    }

    fun closeAddExamples() {

        uiState = uiState.copy(
            addExamplesSenseId = null
        )

        addExamplesUiState =
            AddUserExamplesUiState()
    }
}