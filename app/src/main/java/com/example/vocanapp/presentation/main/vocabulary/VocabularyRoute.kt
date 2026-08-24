package com.example.vocanapp.presentation.main.vocabulary

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vocan.presentation.auth.login.VocabularyScreen
import com.example.vocanapp.presentation.main.vocabulary.components.addWord.VocabularyEvent

@Composable
fun VocabularyRoute(
    onWordClick: (Int) -> Unit,
    onIdentifyWordsClick: () -> Unit
) {

    val viewModel: VocabularyViewModel = hiltViewModel()

    val uiState = viewModel.uiState
    val addWordUiState = viewModel.addWordUiState

    var showAddWordSheet by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect (Unit) {

        viewModel.events.collect { event ->

            when (event) {

                VocabularyEvent.WordAdded -> {

                    showAddWordSheet = false

                    viewModel.resetAddWordState()

                    viewModel.refreshWords()
                }
            }
        }
    }

    VocabularyScreen(
        uiState = uiState,
        addWordUiState = addWordUiState,

        onLanguageSelected = { language ->
            viewModel.loadWordsByLanguage(language)
        },

        onSearchChanged = { query ->
            viewModel.search(query)
        },

        onWordClick = onWordClick,

        onWordChanged = { word ->
            viewModel.onWordChanged(word)
        },

        onAddLanguageSelected = { language ->
            viewModel.onAddLanguageSelected(language)
        },

        onConfirmAddWord = {
            viewModel.addWord()
        },

        onLoadMore = {
            viewModel.loadMore()
        },

        onAddWordClick = {
            showAddWordSheet = true
        },

        showAddWordSheet = showAddWordSheet,

        onAddWordDismiss = {
            showAddWordSheet = false
        },
        onIdentifyWordsClick = onIdentifyWordsClick
    )
}