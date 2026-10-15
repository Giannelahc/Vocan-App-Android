package com.example.vocanapp.presentation.main.vocabulary.detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.vocanapp.domain.model.Synonym

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SynonymsSection(
    synonyms: List<Synonym>
) {

    DetailSection(
        title = "Synonyms"
    ) {

        FlowRow (
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            synonyms.forEach { synonym ->

                AssistChip(
                    onClick = {},
                    label = {
                        Text(synonym.word)
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    border = BorderStroke(
                        1.dp,
                        MaterialTheme.colorScheme.outline
                    )
                )
            }
        }
    }
}