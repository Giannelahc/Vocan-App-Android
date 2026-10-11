package com.example.vocanapp.presentation.main.vocabulary.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType.Companion.PrimaryNotEditable
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.vocanapp.domain.model.Language

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageSelector(
    selectedLanguage: Language?,
    languages: List<Language>,
    onLanguageSelected: (Language?) -> Unit,
    enabled: Boolean = true,
    compact: Boolean = false
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    if (compact) {

        Box(
            modifier = Modifier.wrapContentSize()
        ) {

            TextButton(
                onClick = {
                    if (enabled) {
                        expanded = !expanded
                    }
                },
                enabled = enabled,
                contentPadding = PaddingValues(
                    horizontal = 8.dp,
                    vertical = 4.dp
                ),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color(0xFFEEF3F7)
                ),
                shape = RoundedCornerShape(5.dp)
            ) {

                Text(
                    text = selectedLanguage?.code?.uppercase() ?: "ALL",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )

                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            LanguageDropdownMenu(
                expanded = expanded,
                languages = languages,
                onLanguageSelected = {
                    onLanguageSelected(it)
                    expanded = false
                },
                onDismissRequest = {
                    expanded = false
                }
            )
        }

    } else {

        // Full selector
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                if (enabled) {
                    expanded = !expanded
                }
            }
        ) {

            OutlinedTextField(
                value = selectedLanguage?.name ?: "All languages",
                onValueChange = {},
                readOnly = true,
                enabled = enabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(
                        type = PrimaryNotEditable,
                        enabled),
                label = {
                    Text("Language")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Language,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFEDFAFF),
                    unfocusedContainerColor = Color(0xFFEDFAFF),
                    disabledContainerColor = Color(0xFFEDFAFF)
                )
            )

            ExposedDropdownMenu(
                expanded = expanded && enabled,
                onDismissRequest = {
                    expanded = false
                }
            ) {

                LanguageMenuItems(
                    languages = languages,
                    onLanguageSelected = {
                        onLanguageSelected(it)
                        expanded = false
                    }
                )
            }
        }
    }
}