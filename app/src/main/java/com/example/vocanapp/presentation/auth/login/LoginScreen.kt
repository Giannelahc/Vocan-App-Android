package com.example.vocan.presentation.auth.login

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.vocanapp.R
import com.example.vocanapp.presentation.auth.login.LoginUiState
import com.example.vocanapp.presentation.main.LettersLoading

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onLoginClick: (String, String) -> Unit,
    onRegisterClick: () -> Unit
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(R.drawable.michita_icon_login),
            contentDescription = "Happy cat",
            modifier = Modifier.size(150.dp)
        )

        Text(
            text = "Michita",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Vocabulary Assistant",
            style = MaterialTheme.typography.titleLarge,
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
            value = email,
            onValueChange = {
                email = it
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
            value = password,
            onValueChange = {
                password = it
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

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Forgot password?",
            modifier = Modifier.align(Alignment.End),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = {
                onLoginClick(email, password)
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
                        modifier = Modifier.size(70.dp)
                    )
                }

            } else {

                Text(
                    text = "Log in",
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

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Don't have an account?",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Create account",
            modifier = Modifier.padding(8.dp)
                .clickable {
                    onRegisterClick()
                },
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelLarge
        )
    }
}