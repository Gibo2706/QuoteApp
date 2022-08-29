package com.bm.quotesapp.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bm.quotesapp.architecture.QuotesState
import com.bm.quotesapp.architecture.QuotesViewModel
import com.bm.quotesapp.ui.MainQuoteUI
import com.bm.quotesapp.ui.categories.CategoriesView

@Composable
fun AppNavGraph(
    state: QuotesState,
    viewModel: QuotesViewModel,
    navController: NavHostController
) {
    NavHost(navController, Routes.HOME){
        composable(Routes.HOME) {
            MainQuoteUI(
                state = state,
                viewModel = viewModel,
                onAction = viewModel::onAction,
                modifier = Modifier
                    .background(color = MaterialTheme.colors.background)
                    .fillMaxSize()
            )
        }
        composable(Routes.CATEGORIES){
            CategoriesView(
                onAction = viewModel::onAction,
                modifier = Modifier
                    .background(color = MaterialTheme.colors.background)
                    .fillMaxSize()
            )
        }
    }
}