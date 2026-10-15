package com.example.vocanapp.presentation.main.review.session.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.vocanapp.R
import com.example.vocanapp.domain.model.ReviewResult
import com.example.vocanapp.domain.model.ReviewStatus
import com.example.vocanapp.presentation.util.formatReviewDate

@Composable
fun ReviewResultContent(
    result: ReviewResult,
    onDoneClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val completed =
        result.status == ReviewStatus.PASSED

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (completed) {

            Image(
                painter = painterResource(
                    id = R.drawable.happy_michita
                ),
                contentDescription = "Happy cat",
                modifier = Modifier.size(160.dp)
            )

        } else {

            Image(
                painter = painterResource(
                    id = R.drawable.michita_bad_review
                ),
                contentDescription = "Happy cat",
                modifier = Modifier.size(160.dp)
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = if (completed) {
                "Review completed!"
            } else {
                "Keep practicing!"
            },
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = if (completed) {
                "Great job! You completed this review successfully."
            } else {
                "Some answers were incorrect. This review will need another attempt."
            },
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Next review at ${result.nextReviewAt.formatReviewDate()}",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Button(
            onClick = onDoneClick,
            modifier = Modifier.width(90.dp),
            colors = ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.background
            )
        ) {
            Text("Done")
        }
    }
}