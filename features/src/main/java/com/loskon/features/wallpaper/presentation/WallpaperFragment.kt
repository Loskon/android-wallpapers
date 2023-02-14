package com.loskon.features.wallpaper.presentation

import android.app.WallpaperManager
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.loskon.base.extension.coroutines.observe
import com.loskon.base.extension.view.setDebounceClickListener
import com.loskon.base.viewbinding.viewBinding
import com.loskon.features.databinding.FragmentWallpaperBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WallpaperFragment : Fragment(com.loskon.features.R.layout.fragment_wallpaper) {

    private val binding by viewBinding(FragmentWallpaperBinding::bind)
    private val viewModel by viewModel<WallpaperViewModel>()
    private val args by navArgs<WallpaperFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.getWallpaperBitmap(requireContext(), args.imageUrl!!)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //args.imageUrl?.let { ImageLoad.load(binding.imageView2, it) }

        binding.button.setDebounceClickListener {
            val wallpaperManager = WallpaperManager.getInstance(requireContext())
            try {
                binding.imageView2.setImageBitmap(viewModel.getWallpaperBitmap.value)
                wallpaperManager.setBitmap(viewModel.getWallpaperBitmap.value)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        installObserver()
    }

    private fun installObserver() {
        viewModel.getWallpaperState.observe(viewLifecycleOwner) {
            when (it) {
                is WallpaperState.Loading -> {
                    //binding.indicatorWallpaperList.isVisible = true
                }
                is WallpaperState.Success -> {
                    //binding.indicatorWallpaperList.isVisible = false
                    binding.imageView2.setImageBitmap(it.bitmap)
                }
                is WallpaperState.Failure -> {
                    //binding.indicatorWallpaperList.isVisible = false
                    //showWarningSnackbar()
                }
            }
        }
    }
}