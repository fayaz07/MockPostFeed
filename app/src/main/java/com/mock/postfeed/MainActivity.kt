package com.mock.postfeed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mock.postfeed.data.network.PostModel
import com.mock.postfeed.ui.theme.MockPostFeedTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MockPostFeedTheme {
                Content()
            }
        }
    }

    @Composable
    fun Content() {
        val mainViewModel = MainViewModel()
        val state = mainViewModel.state.collectAsState()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {

            if (state.value.posts.isEmpty()) {
                Box {
                    Button(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                            .height(48.dp)
                            .width(200.dp),
                        onClick = {
                            mainViewModel.getPosts()
                        }) {
                        Text(text = "Get data")
                    }
                }
            } else {
                LazyColumn {
                    items(state.value.posts.size) { index ->
                        PostWidget(state.value.posts[index])
                    }
                }
            }
        }
    }

    @Composable
    fun PostWidget(post: PostModel) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(220.dp)
        ) {
            Column {
                Text(text = post.username, style = MaterialTheme.typography.h5)
                AsyncImage(
                    model = post.image,
                    contentDescription = "Post by ${post.username}"
                )
            }
        }
    }
}
