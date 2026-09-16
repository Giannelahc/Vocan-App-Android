package com.example.vocanapp.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MainNavigationBar(
    navController: NavHostController
) {

    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute =
        currentBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color(0xFF386585)
    ) {

        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = {
                if (!navController.popBackStack("home", false)) {
                    navController.navigate("home") {
                        launchSingleTop = true
                    }
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home"
                )
            },
            label = {
                Text("Home")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.7f),
                unselectedTextColor = Color.White.copy(alpha = 0.7f),
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            selected = currentRoute == "vocabulary",
            onClick = {
                navController.navigate("vocabulary") {
                    popUpTo("home") {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.MenuBook,
                    contentDescription = "Vocabulary"
                )
            },
            label = {
                Text("Vocabulary")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.7f),
                unselectedTextColor = Color.White.copy(alpha = 0.7f),
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            selected = currentRoute == "review",
            onClick = {
                navController.navigate("review") {
                    popUpTo("home") {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.AddTask,
                    contentDescription = "Review"
                )
            },
            label = {
                Text("Review")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.7f),
                unselectedTextColor = Color.White.copy(alpha = 0.7f),
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            selected = currentRoute == "profile",
            onClick = {
                navController.navigate("profile") {
                    popUpTo("home") {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile"
                )
            },
            label = {
                Text("Profile")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color.White,
                unselectedIconColor = Color.White.copy(alpha = 0.7f),
                unselectedTextColor = Color.White.copy(alpha = 0.7f),
                indicatorColor = Color.Transparent
            )
        )
    }
}