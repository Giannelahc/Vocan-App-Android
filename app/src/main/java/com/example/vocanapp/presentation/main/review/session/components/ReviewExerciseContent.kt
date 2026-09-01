package com.example.vocanapp.presentation.main.review.session.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vocanapp.domain.model.Exercise

@Composable
fun ReviewExerciseContent(
    exercise: Exercise,
    exerciseNumber: Int,
    totalExercises: Int,
    selectedAnswer: Int?,
    isLastExercise: Boolean,
    isSubmitting: Boolean,
    onAnswerSelected: (Int) -> Unit,
    onNextClick: () -> Unit,
    onFinishClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Exercise $exerciseNumber of $totalExercises",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = exercise.question,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(
            modifier = Modifier.height(28.dp)
        )

        exercise.options.forEachIndexed { index, option ->

            ReviewOption(
                text = option,
                selected = selectedAnswer == index,
                onClick = {
                    onAnswerSelected(index)
                }
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )
        }

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Button(
            onClick = {
                if (isLastExercise) {
                    onFinishClick()
                } else {
                    onNextClick()
                }
            },
            enabled = selectedAnswer != null && !isSubmitting,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            Text(
                text = if (isLastExercise) {
                    "Finish review"
                } else {
                    "Next"
                }
            )
        }
    }
}