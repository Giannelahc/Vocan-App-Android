package com.example.vocanapp.presentation.main.vocabulary.wordidentification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vocanapp.domain.model.Language
import com.example.vocanapp.presentation.main.vocabulary.components.LanguageSelector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdentifyWordsScreen(
    uiState: IdentifyWordsUiState,
    languages: List<Language>,
    onBackClick: () -> Unit,
    onLanguageSelected: (Language?) -> Unit,
    onSentenceChanged: (String) -> Unit,
    onIdentifyClick: () -> Unit,
    onWordSelected: (String) -> Unit,
    onAddWordsClick: () -> Unit
) {

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text("Identify words")
                },
                navigationIcon = {
                    IconButton (
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        bottomBar = {

            if (uiState.selectedWordIds.isNotEmpty()) {

                Button (
                    onClick = onAddWordsClick,
                    enabled = !uiState.isAdding,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 12.dp
                        ),
                    shape = RoundedCornerShape(16.dp)
                ) {

                    if (uiState.isAdding) {

                        CircularProgressIndicator(
                            modifier = Modifier.size(22.dp),
                            strokeWidth = 2.dp
                        )

                    } else {

                        Text(
                            text = "Add ${uiState.selectedWordIds.size} words"
                        )
                    }
                }
            }
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 8.dp,
                bottom = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "Identify vocabulary",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            item {
                Text(
                    text = "Find useful words and expressions from a sentence or text.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            item {
                Text(
                    text = "Language",
                    style = MaterialTheme.typography.labelLarge
                )
            }

            item {
                LanguageSelector(
                    selectedLanguage = uiState.language,
                    languages = languages,
                    onLanguageSelected = onLanguageSelected,
                    enabled = !uiState.isLoading
                )
            }

            item {
                Text(
                    text = "Sentence",
                    style = MaterialTheme.typography.labelLarge
                )
            }

            item {
                OutlinedTextField(
                    value = uiState.sentence,
                    onValueChange = onSentenceChanged,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 100.dp),
                    placeholder = {
                        Text("Enter a sentence or a short text...")
                    },
                    enabled = !uiState.isLoading,
                    shape = RoundedCornerShape(16.dp),
                    maxLines = 6
                )
            }

            item {
                Button(
                    onClick = onIdentifyClick,
                    enabled =
                        uiState.language != null &&
                                uiState.sentence.isNotBlank() &&
                                !uiState.isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(22.dp),
                            strokeWidth = 2.dp
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Identify words")
                    }
                }
            }

            if (uiState.error != null) {
                item {
                    Text(
                        text = uiState.error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            if (uiState.options.isNotEmpty()) {

                item {
                    Column {
                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Words found",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text(
                            text = "Select the words you want to add to your vocabulary.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                items(
                    items = uiState.options,
                    key = { it.baseForm }
                ) { word ->

                    IdentifyWordCard(
                        word = word,
                        selected = word.baseForm in uiState.selectedWordIds,
                        onClick = {
                            onWordSelected(word.baseForm)
                        }
                    )
                }
            }
        }
    }
}