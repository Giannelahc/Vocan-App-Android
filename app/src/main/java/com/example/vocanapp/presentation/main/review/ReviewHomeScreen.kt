package com.example.vocanapp.presentation.main.review

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.vocanapp.presentation.main.review.components.EmptyReviewsCard
import com.example.vocanapp.presentation.main.review.components.ReviewStatisticsSection
import com.example.vocanapp.presentation.main.review.components.ReviewSummaryCard

@Composable
fun ReviewHomeScreen(
    uiState: ReviewHomeUiState,
    onReviewClick: (Int) -> Unit,
    onViewAllClick: () -> Unit
) {

    Scaffold { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Reviews",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Keep improving your vocabulary",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            ReviewStatisticsSection(
                statistics = uiState.statistics
            )

            Spacer(
                modifier = Modifier.height(28.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Your reviews",
                    style = MaterialTheme.typography.titleMedium
                )

                TextButton(
                    onClick = onViewAllClick
                ) {
                    Text("View all")
                }
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            if (uiState.pendingReviews.isEmpty()) {

                EmptyReviewsCard()

            } else {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(
                        bottom = 24.dp
                    )
                ) {

                    items(
                        items = uiState.pendingReviews,
                        key = { it.id }
                    ) { review ->

                        ReviewSummaryCard(
                            review = review,
                            onClick = {
                                onReviewClick(review.id)
                            }
                        )
                    }
                }
            }
        }
    }
}