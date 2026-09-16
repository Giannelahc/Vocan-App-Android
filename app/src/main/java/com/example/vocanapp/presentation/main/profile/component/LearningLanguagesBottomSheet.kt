package com.example.vocanapp.presentation.main.profile.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.vocanapp.domain.model.Language

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearningLanguagesBottomSheet(
    selectedLanguages: List<Language>,
    languages: List<Language>,
    onDone: (List<Language>) -> Unit,
    onDismiss: () -> Unit
) {

    var temporarySelection by remember(selectedLanguages) {
        mutableStateOf(selectedLanguages)
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 24.dp)
        ) {

            Text(
                text = "Languages I'm learning",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Select the languages you want to learn",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 400.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                items(
                    items = languages,
                    key = { it.id }
                ) { language ->

                    val selected =
                        temporarySelection.any {
                            it.id == language.id
                        }

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                                temporarySelection =
                                    if (selected) {

                                        temporarySelection.filter {
                                            it.id != language.id
                                        }

                                    } else {

                                        temporarySelection + language
                                    }
                            },
                        shape = RoundedCornerShape(12.dp),
                        color = if (selected) {
                            Color(0xFFBFD6DE)
                        } else {
                            Color(0xFFEEF3F7)
                        },
                        border = BorderStroke(
                            1.dp,
                            Color(0xFF8FB2CC)
                        )
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 16.dp,
                                    vertical = 12.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = null,
                                tint = if (selected) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme
                                        .onSurfaceVariant
                                }
                            )

                            Spacer(
                                modifier = Modifier.width(16.dp)
                            )

                            Text(
                                text = language.name,
                                modifier = Modifier.weight(1f),
                                style = MaterialTheme.typography.bodyLarge
                            )

                            Checkbox(
                                checked = selected,
                                onCheckedChange = {

                                    temporarySelection =
                                        if (selected) {

                                            temporarySelection.filter {
                                                it.id != language.id
                                            }

                                        } else {

                                            temporarySelection + language
                                        }
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color(0xFF426F8F),
                                    uncheckedColor = MaterialTheme.colorScheme
                                        .onSurfaceVariant
                                ),
                            )
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Button(
                onClick = {
                    onDone(temporarySelection)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color(0xFF426F8F),
                    contentColor = Color(0xFFEEF3F7)
                )
            ) {
                Text("Done")
            }
        }
    }
}