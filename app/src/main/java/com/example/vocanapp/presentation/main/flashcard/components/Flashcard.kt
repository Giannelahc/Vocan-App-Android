package com.example.vocanapp.presentation.main.flashcard.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.vocanapp.domain.model.Flashcard

@Composable
fun Flashcard(
    flashcard: Flashcard,
    revealed: Boolean
) {
    val rotation by animateFloatAsState(
        targetValue = if (revealed) 180f else 0f,
        animationSpec = tween(
            durationMillis = 500
        ),
        label = "card_flip"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12 * density
            }
    ) {

        if (rotation <= 90f) {

            FlashcardFront(
                flashcard = flashcard,
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .graphicsLayer {
                        rotationY = 0f
                    }
            )

        } else {

            FlashcardBack(
                flashcard = flashcard,
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .graphicsLayer {
                        rotationY = 180f
                    }
            )
        }
    }
}