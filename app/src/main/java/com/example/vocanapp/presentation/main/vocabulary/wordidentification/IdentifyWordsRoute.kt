package com.example.vocanapp.presentation.main.vocabulary.wordidentification

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun IdentifyWordsRoute(
    onBackClick: () -> Unit,
    onWordsAdded: () -> Unit
) {

    val viewModel: IdentifyWordsViewModel = hiltViewModel()

    val uiState = viewModel.uiState

    LaunchedEffect (Unit) {
        viewModel.events.collect { event ->
            when (event) {
                WordIdentificationEvent.WordsAdded -> {
                    onWordsAdded()
                }
            }
        }
    }

    IdentifyWordsScreen(
        uiState = uiState,

        languages = uiState.languages,

        onBackClick = onBackClick,

        onLanguageSelected = { language ->
            viewModel.onLanguageSelected(language)
        },

        onSentenceChanged = { sentence ->
            viewModel.onSentenceChanged(sentence)
        },

        onIdentifyClick = {
            viewModel.identify()
        },

        onWordSelected = { wordId ->
            viewModel.toggleWordSelection(wordId)
        },

        onAddWordsClick = {
            viewModel.addSelectedWords()
        }
    )
}