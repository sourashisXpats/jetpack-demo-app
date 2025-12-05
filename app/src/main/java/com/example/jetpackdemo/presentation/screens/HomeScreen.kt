package com.example.jetpackdemo.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackdemo.presentation.theme.JetpackDemoTheme

@Composable
fun HomeScreen(
    modifier: Modifier,
    innerPadding: PaddingValues
) {
    Greeting(
        name = "Android",
        modifier = Modifier.padding(innerPadding)
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackDemoTheme {
        Greeting("Android")
    }
}