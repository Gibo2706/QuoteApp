package com.bm.quotesapp.navigation

import androidx.navigation.NavHostController

object Routes {
    const val HOME = "/home"
    const val CATEGORIES = "/categories"
}

class NavigationActions(navController: NavHostController){
    val navigateToHome: () -> Unit = {
        navController.navigate(Routes.HOME)
    }

    val navigateToCategories: () -> Unit = {
        navController.navigate(Routes.CATEGORIES)
    }

}