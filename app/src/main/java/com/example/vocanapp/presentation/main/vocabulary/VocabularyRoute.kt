package com.example.vocanapp.presentation.main.vocabulary

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.example.vocan.presentation.auth.login.VocabularyScreen
import com.example.vocanapp.presentation.main.vocabulary.components.addWord.VocabularyEvent

@Composable
fun VocabularyRoute(
    navBackStackEntry: NavBackStackEntry,
    onWordClick: (Int) -> Unit,
    onIdentifyWordsClick: () -> Unit
) {

    val viewModel: VocabularyViewModel = hiltViewModel()

    val uiState = viewModel.uiState
    val addWordUiState = viewModel.addWordUiState

    var showAddWordSheet by rememberSaveable {
        mutableStateOf(false)
    }

    val snackbarHostState = remember {
        SnackbarHostState()
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

    LaunchedEffect(navBackStackEntry) {

        navBackStackEntry
            .savedStateHandle
            .getStateFlow("words_added", false)
            .collect { wordsAdded ->

                if (wordsAdded) {

                    viewModel.refreshWords()

                    navBackStackEntry
                        .savedStateHandle
                        .set("words_added", false)
                }
            }
    }

    LaunchedEffect(navBackStackEntry) {

        navBackStackEntry
            .savedStateHandle
            .getStateFlow("content_regenerated", false)
            .collect { regenerated ->

                if (regenerated) {

                    //viewModel.refreshWords()

                    snackbarHostState.showSnackbar(
                        message = "Content regenerated successfully"
                    )

                    navBackStackEntry
                        .savedStateHandle
                        .set("content_regenerated", false)
                }
            }
    }

    LaunchedEffect(navBackStackEntry) {

        navBackStackEntry
            .savedStateHandle
            .getStateFlow("word_deleted", false)
            .collect { deleted ->

                if (deleted) {

                    viewModel.refreshWords()

                    navBackStackEntry
                        .savedStateHandle
                        .set("word_deleted", false)
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
        onIdentifyWordsClick = onIdentifyWordsClick,
        snackbarHostState = snackbarHostState,
        onDeleteWord = { wordId ->
            viewModel.deleteWord(wordId)
        }
    )
}