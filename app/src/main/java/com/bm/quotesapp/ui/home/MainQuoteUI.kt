package com.bm.quotesapp.ui

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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.bm.quotesapp.architecture.QuotesState
import com.bm.quotesapp.architecture.UIAction

import kotlin.math.roundToInt


@Composable
fun MainQuoteUI(
    state: QuotesState,
    viewModel: ViewModel,
    modifier: Modifier = Modifier,
    onAction: (UIAction) -> Unit,
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
                                offsetX += delta / 1.75f
                                if (offsetX > 300f || offsetX < -300f) {
                                    offsetX = 0f
                                    onAction(UIAction.SwipeForNewQuote)

                                }
                            }
                        ),
                        onDragStopped = {
                            offsetX = 0f
                        },
                        onDragStarted = {
                            offsetX = 0f
                        },
                        startDragImmediately = false
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
                                text = "\"$it\"",
                                modifier = Modifier
                                    .padding(horizontal = 16.dp),
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                                letterSpacing = 1.5.sp,
                                color = MaterialTheme.colors.onSurface,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.h1,
                                softWrap = true
                            )
                        }
                        Divider(
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                            thickness = 1.dp
                        )
                        state.author?.let {
                            Text(
                                text = "Author: $it",
                                modifier = Modifier.padding(horizontal = 16.dp),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
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
                        onAction(UIAction.SwipeForNewQuote)
                        offsetX = 0f
                              },
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(text = "New Quote")
                }
                val mContext = LocalContext.current
                Button(
                    onClick = { onAction(UIAction.ShareQuote(mContext)) },
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(text = "Share")
                }
            }
        }
    }

}

