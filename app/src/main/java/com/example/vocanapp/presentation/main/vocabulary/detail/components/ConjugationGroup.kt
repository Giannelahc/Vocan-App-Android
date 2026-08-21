package com.example.vocanapp.presentation.main.vocabulary.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ConjugationGroup(
    title: String,
    forms: Map<String, String>
) {

    Column (
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        forms.forEach { (person, form) ->

            Row (
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = formatConjugationLabel(person),
                    modifier = Modifier.weight(0.4f),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = form,
                    modifier = Modifier.weight(0.6f),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

private fun formatConjugationLabel(
    value: String
): String {

    return when (value) {
        "first_singular" -> "1st person singular"
        "first_plural" -> "1st person plural"
        "third_plural" -> "3rd person plural"
        else -> value
            .replace("_", " ")
            .replaceFirstChar { it.uppercase() }
    }
}