package com.loskon.features.wallpaper.presentation

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.loskon.base.extension.activity.hideSystemUI
import com.loskon.base.extension.activity.showSystemUI
import com.loskon.base.extension.context.navBarHeightInDp
import com.loskon.base.extension.coroutines.observe
import com.loskon.base.extension.view.setDebounceClickListener
import com.loskon.base.extension.view.setMargins
import com.loskon.base.viewbinding.viewBinding
import com.loskon.features.R
import com.loskon.features.databinding.FragmentWallpaperBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class WallpaperFragment : Fragment(R.layout.fragment_wallpaper) {

    private val binding by viewBinding(FragmentWallpaperBinding::bind)
    private val viewModel by viewModel<WallpaperViewModel>()
    private val args by navArgs<WallpaperFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) args.imageUrl?.let { viewModel.getWallpaperBitmap(requireContext(), it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setMargins(bottom = requireContext().navBarHeightInDp.toInt())
        binding.btnSetWallpaper.setMargins(bottom = requireContext().navBarHeightInDp.toInt())

        installObserver()
        setupViewsListeners()
    }

    private fun installObserver() {
        viewModel.getWallpaperState.observe(viewLifecycleOwner) {
            when (it) {
                is WallpaperState.Loading -> {
                    binding.indicatorWallpaper.isVisible = true
                }
                is WallpaperState.Success -> {
                    binding.indicatorWallpaper.isVisible = false
                    binding.ivWallpaper.setImageBitmap(it.bitmap)
                }
                is WallpaperState.Failure -> {
                    binding.indicatorWallpaper.isVisible = false
                    showMessageSnackbar(getString(R.string.error_loading))
                }
                is WallpaperState.NoInternet -> {
                    binding.indicatorWallpaper.isVisible = false
                    showMessageSnackbar(getString(R.string.no_internet))
                }
            }
        }
    }

    private fun showMessageSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
            .setAnchorView(binding.btnSetWallpaper)
            .show()
    }

    private fun setupViewsListeners() {
        binding.sw.setOnRefreshListener {
            binding.sw.isRefreshing = false
            args.imageUrl?.let { viewModel.getWallpaperBitmap(requireContext(), it) }
        }
        binding.btnBack.setDebounceClickListener {
            findNavController().popBackStack()
        }
        binding.btnSetWallpaper.setDebounceClickListener {
            val wallpaperBitmap = viewModel.getWallpaperBitmap.value

            if (wallpaperBitmap != null) {
                setWallpaper(wallpaperBitmap)
            } else {
                showMessageSnackbar(getString(R.string.failure))
            }
        }
    }

    @Suppress("TooGenericExceptionCaught")
    private fun setWallpaper(wallpaperBitmap: Bitmap) {
        val wallpaperManager = WallpaperManager.getInstance(requireContext())

        try {
            wallpaperManager.setBitmap(wallpaperBitmap)
            showMessageSnackbar(getString(R.string.success))
        } catch (e: Exception) {
            showMessageSnackbar(getString(R.string.failure))
            Timber.d(e)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().hideSystemUI()
    }

    override fun onDetach() {
        super.onDetach()
        requireActivity().showSystemUI(hasLightStatusBar = true)
    }
}