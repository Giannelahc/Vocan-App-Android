package com.example.vocanapp.presentation.main.profile

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Brightness6
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vocanapp.presentation.main.profile.component.ProfilePreferenceItem
import com.example.vocanapp.presentation.main.profile.component.ProfileSectionTitle

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    onNativeLanguageClick: () -> Unit,
    onLearningLanguagesClick: () -> Unit,
    onNotificationsClick: () -> Unit,
    onAppearanceClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onSavePreferences: () -> Unit
) {

    val canSave =
        uiState.nativeLanguage != null &&
                uiState.learningLanguages.isNotEmpty() &&
                !uiState.isSaving

    if (uiState.isLoading) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),

        contentPadding = PaddingValues(
            top = 24.dp,
            bottom = 32.dp
        ),

        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // Header

        item {

            Text(
                text = "Profile",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Surface(
                    modifier = Modifier.size(88.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {

                    Box(
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            modifier = Modifier.size(44.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = uiState.username,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = uiState.email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Learning preferences

        item {

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            ProfileSectionTitle(
                title = "Learning preferences"
            )
        }

        item {

            ProfilePreferenceItem(
                title = "Native language",
                value = uiState.nativeLanguage?.name
                    ?: "Not selected",
                icon = Icons.Default.Language,
                onClick = onNativeLanguageClick
            )
        }

        item {

            ProfilePreferenceItem(
                title = "Languages I'm learning",
                value = if (
                    uiState.learningLanguages.isEmpty()
                ) {
                    "Not selected"
                } else {
                    uiState.learningLanguages.joinToString(", ") {
                        it.name
                    }
                },
                icon = Icons.Default.School,
                onClick = onLearningLanguagesClick
            )
        }

        // Save button

        item {

            Button(
                onClick = onSavePreferences,
                enabled = canSave,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                shape = RoundedCornerShape(14.dp)
            ) {

                if (uiState.isSaving) {

                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp
                    )

                } else {

                    Text("Save changes")
                }
            }
        }

        // Preferences

        item {

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            ProfileSectionTitle(
                title = "Preferences"
            )
        }

        item {

            ProfilePreferenceItem(
                title = "Notifications",
                value = "Review reminders",
                icon = Icons.Default.Notifications,
                onClick = onNotificationsClick
            )
        }

        item {

            ProfilePreferenceItem(
                title = "Appearance",
                value = "System default",
                icon = Icons.Default.Brightness6,
                onClick = onAppearanceClick
            )
        }

        // Account

        item {

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            ProfileSectionTitle(
                title = "Account"
            )
        }

        item {

            ProfilePreferenceItem(
                title = "Log out",
                value = null,
                icon = Icons.AutoMirrored.Filled.Logout,
                tint = MaterialTheme.colorScheme.error,
                onClick = onLogoutClick
            )
        }
    }
}