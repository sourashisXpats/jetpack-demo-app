package com.example.jetpackdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpackdemo.presentation.screens.PostScreen
import com.example.jetpackdemo.presentation.screens.PostViewModel
import com.example.jetpackdemo.presentation.theme.JetpackDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = hiltViewModel<PostViewModel>()
            JetpackDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PostScreen(
                        modifier = Modifier,
                        innerPadding = innerPadding,
                        viewModel = viewModel
                    )
                    /*HomeScreen(
                        modifier = Modifier,
                        innerPadding = innerPadding
                    )*/
                }
            }
        }
    }
}