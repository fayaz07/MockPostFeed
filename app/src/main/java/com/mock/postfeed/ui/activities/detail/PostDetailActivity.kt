package com.mock.postfeed.ui.activities.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier

class PostDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val postId = intent.getIntExtra("postId", 0)
        val viewModel = PostDetailViewModel()

        setContent { Content(postId, viewModel) }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun Content(postId: Int, viewModel: PostDetailViewModel) {
        val state = viewModel.state.collectAsState()
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Post detail") }, navigationIcon = {
                    IconButton(
                        onClick = { finish() }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            "this will take us back to home"
                        )
                    }
                })
            },
            modifier = Modifier.fillMaxSize(),
        ) { _ ->
            Text("Data of post id: $postId will be shown here")
        }
    }
}
