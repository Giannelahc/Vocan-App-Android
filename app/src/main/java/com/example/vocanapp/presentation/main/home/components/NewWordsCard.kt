package com.example.vocanapp.presentation.main.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.vocanapp.domain.model.LanguageStatistics

@Composable
fun NewWordsCard(
    statistics: List<LanguageStatistics>
) {
    val languages = statistics
        .filter { it.newWordsCurrentWeek > 0 }

    val totalNewWords = languages.sumOf {
        it.newWordsCurrentWeek
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.secondary
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {

            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AutoStories,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp),
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                Text(
                    text = "New words this week",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // Main number
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = totalNewWords.toString(),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text(
                    text = "words",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 7.dp)
                )
            }

            if (languages.isNotEmpty()) {

                Spacer(
                    modifier = Modifier.height(15.dp)
                )

                languages.forEachIndexed { index, statistic ->

                    if (index > 0) {
                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )
                    }

                    LanguageProgressRow(
                        languageName = statistic.languageName,
                        count = statistic.newWordsCurrentWeek,
                        total = totalNewWords
                    )
                }
            }
        }
    }
}