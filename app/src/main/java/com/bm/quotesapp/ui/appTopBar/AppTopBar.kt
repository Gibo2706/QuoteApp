package com.bm.quotesapp.ui.appTopBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min


@Composable
fun TopBar(
    title: String,
    onNotificationClick: () -> Unit,
    modifier: Modifier = Modifier,
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(min(56.dp, 56.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ){
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onBackground
        )

    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(min(56.dp, 56.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
    ) {

        Button(
            onClick = onNotificationClick,
            modifier = Modifier
                .padding(8.dp)
                .background(MaterialTheme.colors.background, RoundedCornerShape(50))
                .height(32.dp)
                .width(48.dp),

        ) {
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = "Notifications",
                tint = MaterialTheme.colors.onBackground
            )
        }
    }
}

@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onButtonAction: () -> Unit,
){
    val openDialog = remember {
        mutableStateOf(false)
    }
    TopBar(
        title = title,
        onNotificationClick = {
            openDialog.value = true
        },
        modifier = modifier,
    )
    if (openDialog.value){
        androidx.compose.material3.AlertDialog(
            shape = RoundedCornerShape(8.dp),
            title = {
                Text(
                    text = "Notifications settings",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onBackground
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "Turn on/off notifications or change notification settings",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onBackground
                    )
                    Button(onClick = { onButtonAction() }) {
                        Text(
                            text = "Daily notifications",
                            style = MaterialTheme.typography.button,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    },
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Text(
                        text = "OK",
                        style = MaterialTheme.typography.button,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    },
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Text(
                        text = "Cancel",
                        style = MaterialTheme.typography.button,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            },
            onDismissRequest = {
                openDialog.value = false
            },
            containerColor = MaterialTheme.colors.background,
            textContentColor = MaterialTheme.colors.onBackground,
        )
    }
}