package com.example.vocanapp.presentation.main.vocabulary.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.vocanapp.domain.model.Synonym

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
                    }
                )
            }
        }
    }
}