package com.mock.postfeed.ui.activities.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.mock.postfeed.data.network.PostModel
import com.mock.postfeed.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  private lateinit var adapter: MainAdapter

  private val viewModel: MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    viewModel.getPosts()

    lifecycleScope.launchWhenResumed {
      viewModel.state.collect {
        if (it.isNotEmpty()) {
          adapter.list = it
          binding.progressBar.isVisible = false
        }
      }
    }

    adapter = MainAdapter()

    binding.recyclerview.adapter = adapter


  }
}
