package com.example.kotlin_pract.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlin_pract.R

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    var textFieldTextState by remember { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.sky),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Card {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.current_weather),
                    fontSize = 34.sp
                )
                OutlinedTextField(
                    value = textFieldTextState,
                    onValueChange = { textFieldTextState = it.trim() },
                    label = { Text(text = stringResource(id = R.string.enter_city)) }
                )
                Button(
                    onClick = {
                        if (textFieldTextState.isNotEmpty()) {
                            viewModel.addCity(textFieldTextState)
                            textFieldTextState = ""
                        }
                    }
                ) {
                    Text(text = stringResource(id = R.string.add_city))
                }
            }

        }
    }
}