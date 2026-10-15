package com.example.vocanapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF8FB2CC),
    onPrimary = Color(0xFF172630),
    primaryContainer = Color(0xFF315875),
    onPrimaryContainer = Color(0xFFEDFAFF),

    secondary = Color(0xFF66A2C7),
    onSecondary = Color(0xFF172630),
    secondaryContainer = Color(0xFF426F8F),
    onSecondaryContainer = Color(0xFFE8F1F8),

    tertiary = Color(0xFF438094),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFF203F4D),

    background = Color(0xFF121A20),
    onBackground = Color(0xFFE8F1F8),

    surface = Color(0xFF1B2730),
    onSurface = Color(0xFFE8F1F8),

    surfaceVariant = Color(0xFF315875),
    onSurfaceVariant = Color(0xFFBFD6DE),

    outline = Color(0xFF8FB2CC)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF386585),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFBFD6DE),
    onPrimaryContainer = Color(0xFF315875),

    secondary = Color(0xFF426F8F),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFE8F1F8),
    onSecondaryContainer = Color(0xFF47647A),

    tertiary = Color(0xFF315472),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFEFF5F6),
    onTertiaryContainer = Color(0xFF317270),

    background = Color(0xFFEEF3F7),
    onBackground = Color(0xFF222222),

    surface = Color(0xFFEAEFF6),
    onSurface = Color(0xFF222222),

    surfaceVariant = Color(0xFFEDFAFF),
    onSurfaceVariant = Color(0xFF47647A),

    outline = Color(0xFF8FB2CC)
)

@Composable
fun VocanAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}