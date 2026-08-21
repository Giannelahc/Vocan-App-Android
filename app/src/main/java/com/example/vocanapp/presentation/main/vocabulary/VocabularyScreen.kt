package com.example.vocan.presentation.auth.login


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vocanapp.domain.model.Language
import com.example.vocanapp.presentation.main.vocabulary.VocabularyUiState
import com.example.vocanapp.presentation.main.vocabulary.components.LanguageSelector
import com.example.vocanapp.presentation.main.vocabulary.components.WordCard
import com.example.vocanapp.presentation.main.vocabulary.components.addWord.AddWordBottomSheet
import com.example.vocanapp.presentation.main.vocabulary.components.addWord.AddWordUiState

@Composable
fun VocabularyScreen(
    uiState: VocabularyUiState,
    addWordUiState: AddWordUiState,
    showAddWordSheet: Boolean,

    onLanguageSelected: (Language?) -> Unit,
    onSearchChanged: (String) -> Unit,
    onWordClick: (Int) -> Unit,

    onWordChanged: (String) -> Unit,
    onAddLanguageSelected: (Language?) -> Unit,
    onConfirmAddWord: () -> Unit,

    onAddWordClick: () -> Unit,
    onAddWordDismiss: () -> Unit,

    onLoadMore: () -> Unit
) {
    val listState = rememberLazyListState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddWordClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add word"
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "Vocabulary",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "${uiState.totalWords} words",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // Language selector

            LanguageSelector (
                selectedLanguage = uiState.selectedLanguage,
                languages = uiState.languages,
                onLanguageSelected = onLanguageSelected
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // Search

            OutlinedTextField(
                value = uiState.searchQuery.toString(),
                onValueChange = onSearchChanged,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Search words...")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            // Word list

            if (uiState.isLoading) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

            } else if (uiState.error != null) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.error,
                        color = MaterialTheme.colorScheme.error
                    )
                }

            } else {

                LazyColumn (
                    state = listState,
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(
                        bottom = 88.dp
                    )
                ) {

                    items (
                        items = uiState.words,
                        key = { it.id }
                    ) { word ->

                        WordCard (
                            word = word,
                            onClick = {
                                onWordClick(word.id)
                            }
                        )
                    }

                    if (uiState.isLoadingMore) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (showAddWordSheet) {

        AddWordBottomSheet(
            uiState = addWordUiState,
            languages = uiState.languages,

            onWordChanged = onWordChanged,

            onLanguageSelected = onAddLanguageSelected,

            onAddClick = onConfirmAddWord,

            onDismiss = onAddWordDismiss
        )
    }

    // Load next page
    LaunchedEffect (listState) {

        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collect { lastVisibleIndex ->

            if (lastVisibleIndex != null) {
                if (lastVisibleIndex >= uiState.words.size - 5) {
                    onLoadMore()
                }
            }
        }
    }
}