package com.example.matirialtry2.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.api.load
import com.example.matirialtry2.MainActivity
import com.example.matirialtry2.R
import com.google.android.material.bottomappbar.BottomAppBar


import android.view.*


class PictureOfTheDayFragment : Fragment() {
    private val viewModel by viewModels<PictureOfTheDayViewModel>()

    private lateinit var dailyImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getData().observe(this, { dailyImage -> renderData(dailyImage) })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dailyImageView = view.findViewById(R.id.image_view_picture_of_day)
    }

    private fun renderData(dailyImage: PictureOfTheDayData) {
        when (dailyImage) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = dailyImage.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    // show error - empty link
                } else {
                    dailyImageView.load(url) {
                        lifecycle(this@PictureOfTheDayFragment)
                        error(R.drawable.ic_launcher_background)
                        placeholder(R.drawable.ic_launcher_background)
                    }
                }
            }
            is PictureOfTheDayData.Loading -> {
                // show error
            }
            is PictureOfTheDayData.Error -> {
                // show error
            }
        }
    }

    /*
        private fun setBottomAppBar(view: View) {
            val context = activity as MainActivity
            context.setSupportActionBar(view.findViewById(R.id.bottom_app_bar))
            setHasOptionsMenu(true)

            fab.setOnClickListener {
                if (isMain) {
                    isMain = false

                    bottom_app_bar.navigationIcon = null
                    bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                    fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_back_fab))
                    bottom_app_bar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
                } else {
                    isMain = true
                    bottom_app_bar.navigationIcon =
                        ContextCompat.getDrawable(context, R.drawable.ic_hamburger_menu_bottom_bar)
                    bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                    fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_plus_fab))
                    bottom_app_bar.replaceMenu(R.menu.menu_bottom_bar)
                }
            }
        }
    */
    companion object {
        fun newInstance() = PictureOfTheDayFragment()
        private var isMain = true
    }
}