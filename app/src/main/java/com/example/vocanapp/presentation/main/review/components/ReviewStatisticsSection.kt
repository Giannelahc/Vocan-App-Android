package com.example.vocanapp.presentation.main.review.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.vocanapp.domain.model.ReviewStatistics

@Composable
fun ReviewStatisticsSection(
    statistics: ReviewStatistics
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE3F5FC)
        ),
        border = BorderStroke(
            2.dp,
            Color(0xFF8FB2CC)
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            ReviewStatisticItem(
                value = "${statistics.successRate.toInt()}%",
                label = "Success rate"
            )

            ReviewStatisticItem(
                value = statistics.reviewsToReview.toString(),
                label = "To review"
            )

            ReviewStatisticItem(
                value = statistics.overdueReviews.toString(),
                label = "Overdue"
            )
        }
    }
}