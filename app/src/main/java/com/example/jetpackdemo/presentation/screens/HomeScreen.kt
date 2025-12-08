package com.example.jetpackdemo.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackdemo.presentation.theme.JetpackDemoTheme

@Composable
fun HomeScreen(
    modifier: Modifier,
    innerPadding: PaddingValues
) {
    Greeting(
        modifier = Modifier.padding(innerPadding)
    )
}

@Composable
fun Greeting( modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Welcome to jetpack demo",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackDemoTheme {
        Greeting()
    }
}