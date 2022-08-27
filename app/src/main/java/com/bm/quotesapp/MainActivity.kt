package com.bm.quotesapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import com.bm.quotesapp.ui.theme.QuotesAppTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bm.quotesapp.architecture.QuotesViewModel
import com.bm.quotesapp.ui.MainQuoteUI


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
