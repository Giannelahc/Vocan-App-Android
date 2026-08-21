package com.example.vocanapp.presentation.main.vocabulary.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vocanapp.domain.model.Word

@Composable
fun VocabularyDetailContent(
    word: Word,
    modifier: Modifier = Modifier
) {

    LazyColumn (
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = 20.dp,
            vertical = 20.dp
        ),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        item {
            WordHeader(word)
        }

        word.senses.forEach { sense ->

            item {
                SenseSection(sense)
            }
        }
    }
}