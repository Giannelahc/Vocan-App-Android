package com.example.vocanapp.presentation.main.vocabulary.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.vocanapp.presentation.main.vocabulary.detail.components.VocabularyDetailContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VocabularyDetailScreen(
    uiState: VocabularyDetailUiState,
    onBackClick: () -> Unit,
    onRegenerateContentClick: () -> Unit,
    onDeleteWordClick: () -> Unit,
    onAddExamplesClick: (Int) -> Unit
) {

    var showMenu by remember {
        mutableStateOf(false)
    }

    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Vocabulary")
                },

                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },

                actions = {

                    Box {
                        IconButton(
                            onClick = {
                                showMenu = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More options"
                            )
                        }

                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = {
                                showMenu = false
                            },
                            containerColor = Color(0xFFEEF3F7)
                        ) {

                            DropdownMenuItem(
                                text = {
                                    Text("Regenerate content")
                                },
                                onClick = {
                                    showMenu = false
                                    onRegenerateContentClick()
                                }
                            )

                            HorizontalDivider(
                                color = Color(0xFF8FB2CC),
                                thickness = 1.dp
                            )

                            DropdownMenuItem(
                                text = {
                                    Text("Delete word")
                                },
                                onClick = {
                                    showMenu = false
                                    showDeleteDialog = true
                                }
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->

        when {

            uiState.isLoading -> {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.error != null -> {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.error,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            uiState.word != null -> {

                VocabularyDetailContent(
                    word = uiState.word,
                    modifier = Modifier.padding(innerPadding),
                    onAddExamplesClick = onAddExamplesClick
                )
            }
        }
    }

    if (showDeleteDialog) {

        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
            },

            title = {
                Text("Delete word?")
            },

            text = {
                Text(
                    "Are you sure you want to remove this word from your vocabulary?"
                )
            },

            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        onDeleteWordClick()
                    }
                ) {
                    Text("Delete")
                }
            },

            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}