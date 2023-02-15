package com.loskon.features.categorylist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.google.android.material.snackbar.Snackbar
import com.loskon.base.extension.coroutines.observe
import com.loskon.base.extension.fragment.fragment.requireDrawable
import com.loskon.base.viewbinding.viewBinding
import com.loskon.features.R
import com.loskon.features.databinding.FragmnetCategoryListBinding
import com.loskon.features.model.CategoryModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryListFragment : Fragment(R.layout.fragmnet_category_list) {

    private val binding by viewBinding(FragmnetCategoryListBinding::bind)
    private val viewModel by viewModel<CategoryViewModel>()
    private val categoryListAdapter = CategoryListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) viewModel.setCategoryMap(getCategoryList())
    }

    private fun getCategoryList(): List<CategoryModel> {
        return listOf(
            CategoryModel("fashion", requireDrawable(R.drawable.fashion)),
            CategoryModel("nature", requireDrawable(R.drawable.nature)),
            CategoryModel("science", requireDrawable(R.drawable.science)),
            CategoryModel("education", requireDrawable(R.drawable.education)),
            CategoryModel("feelings", requireDrawable(R.drawable.feelings)),
            CategoryModel("religion", requireDrawable(R.drawable.religion)),
            CategoryModel("music", requireDrawable(R.drawable.music)),
            CategoryModel("food", requireDrawable(R.drawable.food))
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecyclerView()
        installObserver()
        setupViewsListener()
    }

    private fun configureRecyclerView() {
        with(binding.rvCategoryList) {
            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = categoryListAdapter
            setHasFixedSize(true)
        }
    }

    private fun installObserver() {
        viewModel.getCategoryListState.observe(viewLifecycleOwner) { categoryListAdapter.setItems(it) }
    }

    private fun setupViewsListener() {
        categoryListAdapter.setOnItemClickListener {
            findNavController().navigate(CategoryListFragmentDirections.openWallpaperListFragment(it.title))
        }
        binding.bottomBarCategoryList.setNavigationOnClickListener {
            showMessageSnackbar()
        }
    }

    private fun showMessageSnackbar() {
        Snackbar.make(binding.root, getString(R.string.welcome), Snackbar.LENGTH_LONG)
            .setAnchorView(binding.bottomBarCategoryList)
            .show()
    }
}