package com.example.vocan.presentation.auth.login


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.vocanapp.presentation.main.home.HomeUiState
import com.example.vocanapp.presentation.main.home.components.LearningProgressCard
import com.example.vocanapp.presentation.main.home.components.NewWordsCard
import com.example.vocanapp.presentation.main.home.components.StreakCard
import com.example.vocanapp.presentation.main.home.components.TodayReviewCard

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onReviewClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        modifier = modifier
    ) { innerPadding ->

        if (uiState.isLoading) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

        } else if (uiState.error != null) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = uiState.error,
                    color = MaterialTheme.colorScheme.error
                )
            }

        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    top = 16.dp,
                    bottom = 24.dp
                )
            ) {

                // Greeting

                item {

                    Text(
                        text = "Welcome back, ${uiState.home?.name}!",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                // Streak

                item {

                    StreakCard(
                        streak = uiState.home!!.streak
                    )
                }

                // Learning progress

                item {

                    LearningProgressCard(
                        statistics = uiState.home!!.languageStatistics
                    )
                }

                // Today's review + New words

                item {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        TodayReviewCard(
                            reviewCount = uiState.home!!.todayReviews,
                            onClick = onReviewClick,
                            modifier = Modifier.weight(1f)
                        )

                        NewWordsCard(
                            statistics = uiState.home.languageStatistics,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}