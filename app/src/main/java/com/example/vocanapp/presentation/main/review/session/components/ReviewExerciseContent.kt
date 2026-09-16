package com.example.vocanapp.presentation.main.review.session.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = "Exercise $exerciseNumber of $totalExercises",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(
                text = exercise.question,
                style = MaterialTheme.typography.titleLarge
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

            // Espacio adicional al final del scroll
            Spacer(
                modifier = Modifier.height(24.dp)
            )
        }

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
                .padding(
                    top = 12.dp,
                    bottom = 24.dp
                ),
            colors = ButtonDefaults.textButtonColors(
                containerColor = Color(0xFF426F8F),
                contentColor = Color(0xFFEEF3F7)
            )
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