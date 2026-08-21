package com.example.vocanapp.presentation.main.vocabulary.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TranslationsSection(
    translations: Map<String, List<String>>
) {

    DetailSection(
        title = "Translations"
    ) {

        Column (
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            translations.forEach { (language, words) ->

                Row (
                    verticalAlignment = Alignment.Top
                ) {

                    Text(
                        text = language.uppercase(),
                        modifier = Modifier.width(40.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = words.joinToString(" · "),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}