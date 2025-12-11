package com.example.jetpackdemo.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(),
    viewModel: PostViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsState().value

    PullToRefreshBox(
        isRefreshing = state.isLoading,
        onRefresh = { viewModel.refresh() },
        modifier = Modifier.padding(innerPadding)
    ) {

        if (state.isLoading && state.posts.isEmpty()) {
            CircularProgressIndicator()
            return@PullToRefreshBox
        }

        LazyColumn(
            modifier = modifier.padding(16.dp)
        ) {
            items(state.posts.size) { index ->
                Text(
                    state.posts[index]?.title?.uppercase(Locale.getDefault()) ?: "",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(6.dp))

                Text(
                    state.posts[index]?.body ?: "",
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.Gray
                )

                Spacer(Modifier.height(12.dp))
                HorizontalDivider()
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}


