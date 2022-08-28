package com.mock.postfeed.ui.activities.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.mock.postfeed.data.network.PostModel
import com.mock.postfeed.ui.widgets.MySpinner
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

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
        val scrollSate = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollSate)) {
            SubcomposeAsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = post.image,
                contentDescription = "Post by ${post.username}",
                loading = {
                    MySpinner()
                }
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Row(modifier = Modifier.padding(bottom = 4.dp)) {
                    SubcomposeAsyncImage(
                        model = post.avatar,
                        contentDescription = post.username,
                        loading = {
                            MySpinner()
                        },
                        modifier = Modifier
                            .padding(4.dp)
                            .padding(end = 8.dp)
                            .height(32.dp)
                            .width(32.dp)
                            .clip(CircleShape)
                    )
                    Text(text = post.username, style = MaterialTheme.typography.h5)
                }
                Text(
                    text = "Posted on: ${generalizedDate(post.createdAt)}",
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = "Liked by ${post.likes} people",
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = post.caption,
                    style = MaterialTheme.typography.subtitle1.copy(fontSize = 18.sp),
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun generalizedDate(dateString: String): String {
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(dateString)
        return date?.let { SimpleDateFormat("MMM dd, yyyy").format(it) }.toString()
    }
}
