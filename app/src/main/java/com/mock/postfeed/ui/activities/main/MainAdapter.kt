package com.mock.postfeed.ui.activities.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mock.postfeed.R
import com.mock.postfeed.data.network.PostModel
import com.mock.postfeed.databinding.ItemBinding

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

  var list: List<PostModel> = emptyList()
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
    val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return MainViewHolder(binding)
  }

  override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
    val item = list[position]
    holder.bind(item)
  }

  override fun getItemCount(): Int = list.size
}

class MainViewHolder(
  private val itemBinding: ItemBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

  fun bind(item: PostModel) {
    itemBinding.userTitle.text = item.username

    Glide.with(itemBinding.userAvater.context).load(item.avatar).into(itemBinding.userAvater)
    Glide.with(itemBinding.image.context).load(item.image).into(itemBinding.image)
  }

}