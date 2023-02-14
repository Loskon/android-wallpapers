package com.loskon.features.categorylist

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.loskon.base.extension.view.setDebounceClickListener
import com.loskon.base.viewbinding.viewBinding
import com.loskon.features.databinding.ItemCategoryListBinding
import com.loskon.features.model.CategoryModel

@SuppressLint("NotifyDataSetChanged")
class CategoryListAdapter : RecyclerView.Adapter<CategoryListAdapter.CategoryListViewHolder>() {

    private var list: List<CategoryModel> = listOf()
    private var onItemClick: ((CategoryModel) -> Unit)? = null

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListViewHolder {
        return CategoryListViewHolder(parent.viewBinding(ItemCategoryListBinding::inflate))
    }

    override fun onBindViewHolder(holder: CategoryListViewHolder, position: Int) {
        val item = list[position]

        with(holder.binding) {
            ivCategory.setImageDrawable(item.image)
            tvCategory.text = item.title.replaceFirstChar { it.uppercase() }
            root.setDebounceClickListener { onItemClick?.invoke(item) }
        }
    }

    fun setItems(list: List<CategoryModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClick: ((CategoryModel) -> Unit)?) {
        this.onItemClick = onItemClick
    }

    class CategoryListViewHolder(val binding: ItemCategoryListBinding) : RecyclerView.ViewHolder(binding.root)
}