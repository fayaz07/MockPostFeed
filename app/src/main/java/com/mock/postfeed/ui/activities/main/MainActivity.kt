package com.mock.postfeed.ui.activities.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.mock.postfeed.data.network.PostModel
import com.mock.postfeed.ui.activities.detail.PostDetailActivity
import com.mock.postfeed.ui.theme.MockPostFeedTheme

class MainActivity : ComponentActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MockPostFeedTheme {
                Content()
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun Content() {
        val mainViewModel = MainViewModel()
        val state = mainViewModel.state.collectAsState()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(title = { Text(text = "Home") })
            }
        ) { _ ->
            GetHomeContent(state)
        }
    }

    @Composable
    fun GetHomeContent(state: State<MainActivityState>) {
        if(state.value.loading) {
            MySpinner()
        } else {
            LazyColumn {
                items(state.value.posts.size) { index ->
                    PostWidget(state.value.posts[index])
                }
            }
        }
    }

    @Composable
    fun MySpinner() {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }

    fun onPostClicked(post: PostModel) {
        Log.d(TAG, "Post with id: ${post.id} clicked")
        val intent = Intent(this, PostDetailActivity::class.java)
        intent.putExtra("postId", post.id)
        startActivity(intent)
    }

    @Composable
    fun PostWidget(post: PostModel) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(220.dp)
                .clickable {
                    onPostClicked(post)
                }
        ) {
            Column {
                Text(text = post.username, style = MaterialTheme.typography.h5)
                SubcomposeAsyncImage(
                    model = post.image,
                    contentDescription = "Post by ${post.username}",
                    loading = {
                        MySpinner()
                    }
                )
            }
        }
    }
}
