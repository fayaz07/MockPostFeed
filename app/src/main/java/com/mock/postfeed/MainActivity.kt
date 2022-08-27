package com.mock.postfeed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        val state =  mainViewModel.state.collectAsState()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column {
                Text(text = state.value.posts.toString())
                Button(
                    modifier = Modifier
                        .height(48.dp)
                        .width(200.dp),
                    onClick = {
                        mainViewModel.getPosts()
                    }) {
                    Text(text = "Get data")
                }
            }
        }
    }
}
