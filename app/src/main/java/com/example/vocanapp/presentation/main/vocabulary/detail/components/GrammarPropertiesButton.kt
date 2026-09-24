package com.example.vocanapp.presentation.main.vocabulary.detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun GrammarPropertiesButton(
    properties: Map<String, Any>
) {
    if (properties.isEmpty()) return

    var showProperties by remember {
        mutableStateOf(false)
    }

    Box {
        IconButton(
            onClick = {
                showProperties = !showProperties
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = "Grammar properties"
            )
        }

        if (showProperties) {
            GrammarPropertiesPopup(
                properties = properties,
                onDismiss = {
                    showProperties = false
                }
            )
        }
    }
}