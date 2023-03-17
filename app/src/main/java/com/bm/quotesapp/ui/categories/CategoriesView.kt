package com.bm.quotesapp.ui.categories

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
    val cats1 = remember {
        listOf("Motivational", "Inspirational", "Love", "Technology", "Life")
    }


    val cats2 = remember{
        listOf("Success", "Friendship", "Wisdom", "Happiness", "Funny")
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopStart
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    cats1.forEach { cat ->
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, Color.Gray),
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    onAction(UIAction.CategorySelected(cat))
                                    openDialog.value = true
                                }
                                .width(180.dp)
                        ) {
                            Text(
                                text = cat,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                    }
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    cats2.forEach { cat ->
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, Color.Gray),
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    onAction(UIAction.CategorySelected(cat))
                                    openDialog.value = true
                                }
                                .width(180.dp)
                        ) {
                            Text(
                                text = cat,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                    }
                }
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