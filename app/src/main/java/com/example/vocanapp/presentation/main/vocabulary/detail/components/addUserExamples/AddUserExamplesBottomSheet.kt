package com.example.vocanapp.presentation.main.vocabulary.detail.components.addUserExamples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vocanapp.presentation.main.LettersLoading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUserExamplesBottomSheet(
    examples: List<String>,
    isSaving: Boolean,
    onExampleChanged: (Int, String) -> Unit,
    onAddExample: () -> Unit,
    onSave: () -> Unit,
    onDismiss: () -> Unit
) {

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
                text = "Add examples",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(
                        weight = 1f,
                        fill = false
                    ),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                itemsIndexed(examples) { index, example ->

                    OutlinedTextField(
                        value = example,
                        onValueChange = {
                            onExampleChanged(index, it)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Example ${index + 1}")
                        },
                        placeholder = {
                            Text("Write an example sentence...")
                        },
                        minLines = 2,
                        maxLines = 4,
                        shape = RoundedCornerShape(12.dp)
                    )
                }

                item {

                    TextButton(
                        onClick = onAddExample
                    ) {

                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )

                        Spacer(
                            modifier = Modifier.width(6.dp)
                        )

                        Text("Add another example")
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                TextButton(
                    onClick = onDismiss,
                    enabled = !isSaving
                ) {
                    Text("Cancel")
                }

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Button(
                    onClick = onSave,
                    enabled =
                        examples.any { it.isNotBlank() } &&
                                !isSaving
                ) {

                    if (isSaving) {

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            LettersLoading(
                                modifier = Modifier.size(40.dp)
                            )
                        }

                    } else {

                        Text("Save")
                    }
                }
            }
        }
    }
}