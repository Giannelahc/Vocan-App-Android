package com.example.vocanapp.presentation.main.vocabulary.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vocan.presentation.auth.login.HomeScreen
import com.example.vocan.presentation.auth.login.VocabularyScreen
import com.example.vocan.presentation.auth.register.RegisterScreen
import com.example.vocanapp.presentation.auth.register.RegisterViewModel
import com.example.vocanapp.presentation.main.home.HomeViewModel
import com.example.vocanapp.presentation.main.vocabulary.VocabularyViewModel
import com.example.vocanapp.presentation.main.vocabulary.detail.components.addUserExamples.AddUserExamplesBottomSheet

@Composable
fun VocabularyDetailRoute(
    wordId: Int,
    onBackClick: () -> Unit,
    onContentRegenerated: () -> Unit,
    onWordDeleted: () -> Unit
) {

    val viewModel: VocabularyDetailViewModel = hiltViewModel()
    val uiState = viewModel.uiState
    val addExamplesUiState = viewModel.addExamplesUiState

    LaunchedEffect(wordId) {
        viewModel.loadWord(wordId)
    }

    LaunchedEffect(Unit) {

        viewModel.events.collect { event ->

            when (event) {

                VocabularyDetailEvent.ContentRegenerated -> {
                    onContentRegenerated()
                }

                VocabularyDetailEvent.WordDeleted -> {
                    onWordDeleted()
                }
            }
        }
    }

    VocabularyDetailScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        onRegenerateContentClick = {
            viewModel.regenerateContent()
        },

        onDeleteWordClick = {
            viewModel.deleteWord()
        },
        onAddExamplesClick = { senseId ->
            viewModel.openAddExamples(senseId)
        }
    )

    if (uiState.addExamplesSenseId != null) {

        AddUserExamplesBottomSheet(
            examples = addExamplesUiState.examples,

            isSaving = addExamplesUiState.isSaving,

            onExampleChanged = { index, value ->
                viewModel.updateExample(
                    index = index,
                    value = value
                )
            },

            onAddExample = {
                viewModel.addExampleField()
            },

            onSave = {
                viewModel.saveExamples()
            },

            onDismiss = {
                viewModel.closeAddExamples()
            }
        )
    }
}