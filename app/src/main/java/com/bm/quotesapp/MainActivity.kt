package com.bm.quotesapp

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.bm.quotesapp.ui.theme.QuotesAppTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bm.quotesapp.architecture.home.QuotesViewModel
import com.bm.quotesapp.navigation.AppNavGraph
import com.bm.quotesapp.navigation.NavigationActions
import com.bm.quotesapp.navigation.Routes
import com.bm.quotesapp.notifications.Notifications
import com.bm.quotesapp.receivers.AlarmReceiver
import com.bm.quotesapp.ui.appBottomBar.AppBottomBar



private const val HALF_DAY = 43200000L

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notificationManager: NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        Notifications().initChannel(notificationManager = notificationManager)


            setContent {
            QuotesAppTheme {
                val viewModel = viewModel<QuotesViewModel>()
                val state = viewModel.state

                viewModel.status.observe(this) { status ->
                    status?.let {
                        viewModel.status.value = null
                        Toast.makeText(this, "Loading new quote", Toast.LENGTH_SHORT).show()
                    }
                }

                val navController = rememberNavController()

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = remember(navBackStackEntry){
                    navBackStackEntry?.destination?.route ?: Routes.HOME
                }

                val navigationActions = remember(navController) {
                    NavigationActions(navController)
                }
                
                Scaffold(
                    bottomBar = {
                        AppBottomBar(
                            currentRoute = currentRoute,
                            navigateToHome = navigationActions.navigateToHome,
                            navigateToInterests = navigationActions.navigateToCategories,
                            )
                    }
                ){
                    AppNavGraph(
                        state = state,
                        viewModel = viewModel,
                        navController = navController)
                }


            }
        }
    }
}

