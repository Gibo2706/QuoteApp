package com.bm.quotesapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.bm.quotesapp.managers.RequestManager
import com.bm.quotesapp.ui.theme.QuotesAppTheme
import androidx.lifecycle.viewmodel.compose.viewModel



class MainActivity : ComponentActivity() {
    var content: String? = ""
    var author: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotesAppTheme {
                val viewModel = viewModel<QuotesViewModel>()
                val state = viewModel.state
                viewModel.status.observe(this, Observer {
                    status -> status?.let {
                        viewModel.status.value = null
                        Toast.makeText(this, "Loading new quote", Toast.LENGTH_SHORT).show()
                }
                })
                MainQuoteUI(
                    state = state,
                    viewModel = viewModel,
                    onAction = viewModel::onAction,
                    modifier = Modifier
                        .background(color = MaterialTheme.colors.background)
                        .fillMaxSize()
                )


            }
        }
    }


}
