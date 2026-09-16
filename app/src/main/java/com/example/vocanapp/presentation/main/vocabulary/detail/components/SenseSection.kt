package com.example.vocanapp.presentation.main.vocabulary.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vocanapp.domain.model.WordSense

@Composable
fun SenseSection(
    sense: WordSense,
    onAddExamplesClick: (Int) -> Unit
) {

    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        HorizontalDivider(
            color = Color(0xFF8FB2CC),
            thickness = 2.dp
        )

        Text(
            text = sense.grammarType
                .replace("_", " ")
                .replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )

        DetailSection(
            title = "Meaning"
        ) {
            Text(
                text = sense.definition,
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = 24.sp
            )
        }

        if (sense.translations.isNotEmpty()) {
            TranslationsSection(
                translations = sense.translations
            )
        }

        if (sense.examples.isNotEmpty()) {
            ExamplesSection(
                examples = sense.examples.map { it.sentence }
            )
        }

        if (sense.synonyms.isNotEmpty()) {
            SynonymsSection(
                synonyms = sense.synonyms
            )
        }

        sense.conjugation?.let {
            ConjugationSection(
                conjugation = it
            )
        }

        ExamplesSection(
            title = "My examples",
            examples = sense.userExamples?.map { it.sentence } ?: emptyList(),
            onAddClick = {
                onAddExamplesClick(sense.id)
            }
        )

    }
}