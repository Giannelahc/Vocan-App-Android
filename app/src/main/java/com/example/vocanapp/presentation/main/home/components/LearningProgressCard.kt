package com.example.vocanapp.presentation.main.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.vocanapp.domain.model.LanguageStatistics

@Composable
fun LearningProgressCard(
    statistics: List<LanguageStatistics>
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEEF3F7)
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = "Learning progress",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            statistics.forEachIndexed { index, statistic ->

                LanguageProgressItem(
                    languageName = statistic.languageName,
                    learnedWords = statistic.learnedWords,
                    totalWords = statistic.totalWords
                )

                if (index < statistics.lastIndex) {
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                }
            }
        }
    }
}