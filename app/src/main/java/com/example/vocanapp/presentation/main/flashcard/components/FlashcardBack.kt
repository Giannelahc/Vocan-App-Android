package com.example.vocanapp.presentation.main.flashcard.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import com.example.vocanapp.domain.model.Flashcard


@Composable
fun FlashcardBack(
    flashcard: Flashcard,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEEF3F7)
        ),
        border = BorderStroke(
            3.dp,
            Color(0xFF8FB2CC)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = flashcard.word.uppercase(),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF426F8F)
                )
            }

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            if (flashcard.synonyms.isNotEmpty()) {

                Text(
                    text = "Synonyms",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                flashcard.synonyms
                    .forEach { synonym ->

                        Text(
                            text = "• $synonym",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            flashcard.example.let { example ->

                Text(
                    text = "Example",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                flashcard.example
                    .forEach { exm ->
                        Text(
                            text = "• $exm",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
            }
        }
    }
}