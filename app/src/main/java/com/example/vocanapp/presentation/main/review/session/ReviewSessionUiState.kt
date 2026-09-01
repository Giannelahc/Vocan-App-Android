package com.example.vocanapp.presentation.main.review.session

import com.example.vocanapp.domain.model.Exercise
import com.example.vocanapp.domain.model.ReviewDetail
import com.example.vocanapp.domain.model.ReviewResult

data class ReviewSessionUiState(
    val isLoading: Boolean = false,
    val isSubmitting: Boolean = false,
    val error: String? = null,

    val review: ReviewDetail? = null,

    val currentExerciseIndex: Int = 0,

    val selectedAnswers: Map<Int, Int> = emptyMap(),

    val result: ReviewResult? = null
) {

    val exercises: List<Exercise>
        get() = review?.exercises ?: emptyList()

    val currentExercise: Exercise?
        get() = exercises.getOrNull(currentExerciseIndex)

    val totalExercises: Int
        get() = exercises.size

    val isLastExercise: Boolean
        get() =
            totalExercises > 0 &&
                    currentExerciseIndex == totalExercises - 1

    val currentAnswer: Int?
        get() =
            currentExercise?.let {
                selectedAnswers[it.id]
            }
}