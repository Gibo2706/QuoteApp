package com.bm.quotesapp.ui.appBottomBar

import android.os.Trace
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.material.MaterialTheme
import com.bm.quotesapp.navigation.Routes

@Composable
fun AppBottomBar(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToInterests: () -> Unit,
){
    Trace.beginSection("bottomNav")
    NavigationBar(
        containerColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
    ){
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Home", color = MaterialTheme.colors.onBackground) },
            selected = Routes.HOME == currentRoute,
            onClick = navigateToHome,
            alwaysShowLabel = Routes.HOME == currentRoute,
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, null) },
            label = { Text("Categories", color = MaterialTheme.colors.onBackground) },
            selected = Routes.CATEGORIES == currentRoute,
            onClick = navigateToInterests,
            alwaysShowLabel = Routes.CATEGORIES == currentRoute
        )
    }
    Trace.endSection()
}

