package com.bm.quotesapp.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bm.quotesapp.architecture.categories.CategoriesViewModel
import com.bm.quotesapp.architecture.home.QuotesState
import com.bm.quotesapp.architecture.home.QuotesViewModel
import com.bm.quotesapp.ui.MainQuoteUI
import com.bm.quotesapp.ui.categories.CategoriesView

@Composable
fun AppNavGraph(
    state: QuotesState,
    viewModel: QuotesViewModel,
    navController: NavHostController,
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
            val viewModelCat = viewModel<CategoriesViewModel>()
            val stateCat = viewModelCat.catState
            CategoriesView(
                onAction = viewModelCat::onAction,
                modifier = Modifier
                    .background(color = MaterialTheme.colors.background)
                    .fillMaxSize(),
                catState = stateCat,
                viewModel = viewModelCat
            )
        }
    }
}