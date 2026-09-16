package com.example.vocanapp.presentation.main.vocabulary.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.vocanapp.domain.model.Language

@Composable
fun LanguageDropdownMenu(
    expanded: Boolean,
    languages: List<Language>,
    onLanguageSelected: (Language?) -> Unit,
    onDismissRequest: () -> Unit
) {

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        containerColor = Color(0xFFEEF3F7),
        shape = RoundedCornerShape(5.dp)
    ) {

        LanguageMenuItems(
            languages = languages,
            onLanguageSelected = onLanguageSelected
        )
    }
}