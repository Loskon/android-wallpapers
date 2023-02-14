package com.loskon.features.wallpaperlist.presentation

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.loskon.base.extension.view.setDebounceClickListener
import com.loskon.base.viewbinding.viewBinding
import com.loskon.features.databinding.ItemWallpaperCardBinding
import com.loskon.features.model.WallpaperModel
import com.loskon.network.imageloader.ImageLoad

@SuppressLint("NotifyDataSetChanged")
class WallpaperListAdapter : RecyclerView.Adapter<WallpaperListAdapter.WallpaperListViewHolder>() {

    private var list: List<WallpaperModel> = emptyList()

    private var onItemClick: ((WallpaperModel) -> Unit)? = null

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperListViewHolder {
        return WallpaperListViewHolder(parent.viewBinding(ItemWallpaperCardBinding::inflate))
    }

    override fun onBindViewHolder(holder: WallpaperListViewHolder, position: Int) {
        val item = list[position]

        item.webformatURL?.let { ImageLoad.load(holder.binding.ivWallpaper, it) }
        holder.binding.root.setDebounceClickListener { onItemClick?.invoke(item) }
    }

    fun setItems(list: List<WallpaperModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClick: ((WallpaperModel) -> Unit)?) {
        this.onItemClick = onItemClick
    }

    class WallpaperListViewHolder(val binding: ItemWallpaperCardBinding) : RecyclerView.ViewHolder(binding.root)
}