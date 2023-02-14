package com.loskon.features.wallpaperlist.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.loskon.base.extension.coroutines.observe
import com.loskon.base.viewbinding.viewBinding
import com.loskon.features.R
import com.loskon.features.databinding.FragmentWallpaperListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WallpaperListFragment : Fragment(R.layout.fragment_wallpaper_list) {

    private val binding by viewBinding(FragmentWallpaperListBinding::bind)
    private val viewModel by viewModel<WallpaperListViewModel>()
    private val args by navArgs<WallpaperListFragmentArgs>()
    private val wallpaperListAdapter = WallpaperListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) { viewModel.getWallpaperList(args.category) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecyclerView()
        installObserver()
    }

    private fun configureRecyclerView() {
        with(binding.rv) {
            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = wallpaperListAdapter
            setHasFixedSize(true)
        }
    }

    private fun installObserver() {
        viewModel.getWallpaperListState.observe(viewLifecycleOwner) {
            when (it) {
                is WallpaperListState.Loading -> {}
                is WallpaperListState.Success -> {
                    wallpaperListAdapter.setItems(it.wallpapers)
                }
                is WallpaperListState.Failure -> {}
            }
        }
    }
}