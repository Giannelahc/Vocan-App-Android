package com.example.vocanapp.presentation.main.review.session

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vocanapp.presentation.main.LettersLoading
import com.example.vocanapp.presentation.main.review.session.components.ReviewExerciseContent
import com.example.vocanapp.presentation.main.review.session.components.ReviewResultContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewSessionScreen(
    uiState: ReviewSessionUiState,
    onBackClick: () -> Unit,
    onAnswerSelected: (Int) -> Unit,
    onNextClick: () -> Unit,
    onFinishClick: () -> Unit,
    onDoneClick: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Review")
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                windowInsets = WindowInsets(0, 0, 0, 0)
            )
        }
    ) { innerPadding ->

        when {

            uiState.isLoading -> {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    LettersLoading(
                        modifier = Modifier.size(70.dp)
                    )
                }
            }

            uiState.error != null -> {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.error,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            uiState.result != null -> {

                ReviewResultContent(
                    result = uiState.result,
                    onDoneClick = onDoneClick,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            uiState.currentExercise != null -> {

                ReviewExerciseContent(
                    exercise = uiState.currentExercise!!,
                    exerciseNumber = uiState.currentExerciseIndex + 1,
                    totalExercises = uiState.totalExercises,
                    selectedAnswer = uiState.currentAnswer,
                    isLastExercise = uiState.isLastExercise,
                    isSubmitting = uiState.isSubmitting,
                    onAnswerSelected = onAnswerSelected,
                    onNextClick = onNextClick,
                    onFinishClick = onFinishClick,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}