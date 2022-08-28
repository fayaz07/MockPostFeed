package com.mock.postfeed.ui.activities.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import coil.compose.SubcomposeAsyncImage
import com.mock.postfeed.data.network.PostModel
import com.mock.postfeed.ui.widgets.MySpinner

class PostDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val postId = intent.getIntExtra("postId", 0)
        val viewModel = PostDetailViewModel()

        viewModel.getPostById(postId)

        setContent { Content(viewModel) }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun Content(viewModel: PostDetailViewModel) {
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
            GetScaffoldContent(state)
        }
    }

    @Composable
    fun GetScaffoldContent(state: State<PostDetailActivityState>) {
        if (state.value.loading) {
            MySpinner()
        } else {
            state.value.postData?.let { GetPostDetail(post = it) }
        }
    }

    @Composable
    fun GetPostDetail(post: PostModel) {
        Column {
            SubcomposeAsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = post.image,
                contentDescription = "Post by ${post.username}",
                loading = {
                    MySpinner()
                }
            )
        }
    }
}
