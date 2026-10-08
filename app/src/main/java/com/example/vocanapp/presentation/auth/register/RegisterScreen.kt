package com.example.vocan.presentation.auth.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.vocanapp.presentation.auth.register.RegisterEvent
import com.example.vocanapp.presentation.auth.register.RegisterUiState
import com.example.vocanapp.presentation.main.LettersLoading

@Composable
fun RegisterScreen(
    uiState: RegisterUiState,
    onEvent: (RegisterEvent) -> Unit,
    onRegisterSuccess: () -> Unit,
    onLoginClick: () -> Unit
) {

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(uiState.isRegistered) {
        if (uiState.isRegistered) {
            onRegisterSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Vocabulary Assistant",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Learn words naturally",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            value = uiState.form.name,
            onValueChange = {
                onEvent(RegisterEvent.NameChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Name")
            },
            singleLine = true,
            enabled = !uiState.isLoading,
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.form.lastname,
            onValueChange = {
                onEvent(RegisterEvent.LastnameChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Lastname")
            },
            singleLine = true,
            enabled = !uiState.isLoading,
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.form.username,
            onValueChange = {
                onEvent(RegisterEvent.UsernameChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Username")
            },
            singleLine = true,
            enabled = !uiState.isLoading,
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.form.email,
            onValueChange = {
                onEvent(RegisterEvent.EmailChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Email")
            },
            singleLine = true,
            enabled = !uiState.isLoading,
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.form.password,
            onValueChange = {
                onEvent(RegisterEvent.PasswordChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Password")
            },
            singleLine = true,
            enabled = !uiState.isLoading,
            shape = RoundedCornerShape(16.dp),
            visualTransformation =
                if (passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
            trailingIcon = {

                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {
                    Icon(
                        imageVector =
                            if (passwordVisible) {
                                Icons.Default.VisibilityOff
                            } else {
                                Icons.Default.Visibility
                            },
                        contentDescription =
                            if (passwordVisible) {
                                "Hide password"
                            } else {
                                "Show password"
                            }
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = {
                onEvent(RegisterEvent.RegisterClicked)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            enabled = !uiState.isLoading,
            shape = RoundedCornerShape(16.dp)
        ) {

            if (uiState.isLoading) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LettersLoading(
                        modifier = Modifier.size(40.dp)
                    )
                }

            } else {

                Text(
                    text = "Sign up",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }

        uiState.error?.let { error ->

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Already have an account? Sign in",
            modifier = Modifier
                .clickable {
                    onLoginClick()
                }
                .padding(8.dp),
            color = MaterialTheme.colorScheme.primary
        )


    }
}