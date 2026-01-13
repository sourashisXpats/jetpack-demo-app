package com.example.jetpackdemo.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.jetpackdemo.R
import kotlinx.coroutines.flow.collectLatest
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(),
    viewModel: PostViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.toastEvent.collectLatest { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    val state = viewModel.state.collectAsState().value
    val doggyState = viewModel.doggyState.collectAsState().value

    PullToRefreshBox(
        isRefreshing = state.isLoading && doggyState.isLoading,
        onRefresh = { viewModel.refresh() },
        modifier = Modifier.padding(innerPadding)
    ) {

        if (state.isLoading && state.posts.isEmpty() && doggyState.isLoading && doggyState.doggy == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return@PullToRefreshBox
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {

            doggyState.doggy?.let { item ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.message)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Post image",
                    modifier = Modifier.height(200.dp),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    error = painterResource(R.drawable.ic_launcher_foreground),
                )
            }

            LazyColumn(
                modifier = modifier
                    .padding(16.dp)
                    .weight(5f)
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
}