package com.example.vocanapp.presentation.main.flashcard.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.SentimentSatisfied
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.ui.graphics.Color
import com.example.vocanapp.domain.model.ReviewRating

@Composable
fun RatingButtons(
    onRatingSelected: (ReviewRating) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        RatingButton(
            text = "Again",
            icon = Icons.Outlined.Refresh,
            color = Color(0xFFD95C5C),
            modifier = Modifier.weight(1f),
            onClick = {
                onRatingSelected(ReviewRating.AGAIN)
            }
        )

        RatingButton(
            text = "Easy",
            icon = Icons.Outlined.SentimentSatisfied,
            color = Color(0xFF4A82A8),
            modifier = Modifier.weight(1f),
            onClick = {
                onRatingSelected(ReviewRating.EASY)
            }
        )

        RatingButton(
            text = "Good",
            icon = Icons.Outlined.CheckCircle,
            color = Color(0xFF4C9A68),
            modifier = Modifier.weight(1f),
            onClick = {
                onRatingSelected(ReviewRating.GOOD)
            }
        )

        RatingButton(
            text = "Hard",
            icon = Icons.Outlined.Warning,
            color = Color(0xFFE39A3B),
            modifier = Modifier.weight(1f),
            onClick = {
                onRatingSelected(ReviewRating.HARD)
            }
        )

    }
}