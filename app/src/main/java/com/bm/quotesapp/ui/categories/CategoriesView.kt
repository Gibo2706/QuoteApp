package com.bm.quotesapp.ui.categories

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bm.quotesapp.architecture.UIAction

@Composable
fun CategoriesView(
    modifier: Modifier = Modifier,
    onAction: (UIAction) -> Unit
){
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

        }
    }
}