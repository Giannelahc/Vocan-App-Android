package com.example.vocanapp.presentation.main.vocabulary.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.vocanapp.domain.model.Language

@Composable
fun LanguageMenuItems(
    languages: List<Language>,
    onLanguageSelected: (Language?) -> Unit
) {

    DropdownMenuItem(
        text = {
            Text("All languages")
        },
        onClick = {
            onLanguageSelected(null)
        }
    )

    languages.forEach { language ->

        HorizontalDivider(
            color = MaterialTheme.colorScheme.outline,
            thickness = 1.dp
        )

        DropdownMenuItem(
            text = {
                Text(language.name)
            },
            onClick = {
                onLanguageSelected(language)
            }
        )
    }
}