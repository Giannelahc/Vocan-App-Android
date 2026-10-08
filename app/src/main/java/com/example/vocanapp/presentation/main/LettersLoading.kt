package com.example.vocanapp.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.imageLoader
import com.example.vocanapp.R

@Composable
fun LettersLoading(
    modifier: Modifier = Modifier
) {

    val imageLoader = LocalContext.current.imageLoader

    AsyncImage(
        model = R.drawable.letters_loading,
        imageLoader = imageLoader,
        contentDescription = "Loading",
        modifier = modifier,
        contentScale = ContentScale.Fit
    )
}