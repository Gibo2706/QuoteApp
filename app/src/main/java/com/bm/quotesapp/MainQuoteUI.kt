package com.bm.quotesapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

@Composable
fun MainQuoteUI(
    state: QuotesState,
    viewModel: ViewModel,
    modifier: Modifier = Modifier,
    onAction: (UIAction) -> Unit
) {
    var offsetX by remember { mutableStateOf(0f) }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier
                    .padding(16.dp)
                    .offset { IntOffset(offsetX.roundToInt(), 0) }
                    .draggable(
                        orientation = Orientation.Horizontal,
                        state = rememberDraggableState(
                            onDelta = { delta ->
                                offsetX += delta
                                if (offsetX > 300f || offsetX < -300f) {
                                    offsetX = 0f
                                    onAction(UIAction.swipeForNewQuote)
                                }
                            }
                        )
                    ),
                border = BorderStroke(
                    color = MaterialTheme.colors.onSurface,
                    width = 1.dp
                ),

                ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        state.content?.let {
                            Text(
                                text = it,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                        Divider(
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                            thickness = 1.dp
                        )
                        state.author?.let {
                            Text(
                                text = it,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }

                    }
                }
            }
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        onAction(UIAction.swipeForNewQuote)
                        offsetX = 0f
                              },
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(text = "New Quote")
                }
                val mContext = LocalContext.current
                Button(
                    onClick = { onAction(UIAction.shareQuote(mContext)) },
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(text = "Share")
                }
            }
        }
    }
}

