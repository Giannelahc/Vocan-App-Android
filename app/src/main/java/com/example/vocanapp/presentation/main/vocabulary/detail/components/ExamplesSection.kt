package com.example.vocanapp.presentation.main.vocabulary.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExamplesSection(
    examples: List<String>,
    title: String = "Examples",
    onAddClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )

            if (onAddClick != null) {

                Spacer(
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = onAddClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add example"
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        if (examples.isEmpty()) {

            Text(
                text = "No examples added yet",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

        } else {

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                examples.forEach { example ->

                    Text(
                        text = "• $example",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}