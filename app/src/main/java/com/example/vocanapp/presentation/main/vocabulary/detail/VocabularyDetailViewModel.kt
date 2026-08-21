package com.example.vocanapp.presentation.main.vocabulary.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocanapp.domain.usecase.GetWordDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VocabularyDetailViewModel @Inject constructor(
    private val getWordUseCase: GetWordDetailUseCase
) : ViewModel() {

    var uiState by mutableStateOf(
        VocabularyDetailUiState()
    )
        private set

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
}