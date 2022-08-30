package com.bm.quotesapp.ui.categories

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bm.quotesapp.architecture.UIAction
import com.bm.quotesapp.architecture.categories.CategoriesState
import com.bm.quotesapp.architecture.categories.CategoriesViewModel

@Composable
fun CategoriesView(
    modifier: Modifier = Modifier,
    onAction: (UIAction) -> Unit,
    viewModel: CategoriesViewModel,
    catState: CategoriesState,
){
    val openDialog = remember {
        mutableStateOf(false)
    }


    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = {
                        onAction(UIAction.CategorySelected("Inspirational"))
                        openDialog.value = true
                    })
                    .aspectRatio(6f),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Color.Blue,
                border = BorderStroke(
                    color = MaterialTheme.colors.onBackground,
                    width = 1.dp
                )
            ) {
                Text(
                    text = "Inspirational",
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = {
                        onAction(UIAction.CategorySelected("Motivational"))
                        openDialog.value = true

                    })
                    .aspectRatio(6f),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Color.Green,
                border = BorderStroke(
                    color = MaterialTheme.colors.onBackground,
                    width = 1.dp
                )
            ) {
                Text(
                    text = "Motivational",
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = {
                        onAction(UIAction.CategorySelected("Love"))
                        openDialog.value = true

                    })
                    .aspectRatio(6f),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Color.Red,
                border = BorderStroke(
                    color = MaterialTheme.colors.onBackground,
                    width = 1.dp
                )
            ) {
                Text(
                    text = "Love",
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = {
                        onAction(UIAction.CategorySelected("Technology"))
                        openDialog.value = true

                    })
                    .aspectRatio(6f),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Color.Magenta,
                border = BorderStroke(
                    color = MaterialTheme.colors.onBackground,
                    width = 1.dp
                )
            ) {
                Text(
                    text = "Technology",
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = {
                        onAction(UIAction.CategorySelected("Life"))
                        openDialog.value = true

                    })
                    .aspectRatio(6f),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Color.Yellow,
                border = BorderStroke(
                    color = MaterialTheme.colors.onBackground,
                    width = 1.dp
                )
            ) {
                Text(
                    text = "Life",
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        val mContext = LocalContext.current
        if(openDialog.value){
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    if(catState.tags.isNotEmpty())
                        Text(text = catState.tags.last().uppercase())
                    else
                        Text(text = "No Category Selected")
                },
                text = {
                    Text(text = catState.content + " - " + catState.author)
                },
                confirmButton = {
                    Button(onClick = {
                        openDialog.value = false
                        onAction(UIAction.ShareQuote(mContext))
                    }) {
                        Text(text = "Share")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        openDialog.value = false
                    }) {
                        Text(text = "Close")
                    }
                },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground,
                shape = RoundedCornerShape(8.dp)
            )
        }
    }
}