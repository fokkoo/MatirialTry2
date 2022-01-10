package com.example.matirialtry2.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.matirialtry2.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class UniverseFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_universe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.view_pager_universe)
        val adapter = UniverseStatePagerAdapter(this)

    adapter.items = UniversePageType.values().toList()
        viewPager.adapter = adapter
        
        viewPager.setPageTransformer(ZoomOutPageTransformer())

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                Log.d("View pager", " onPageScrolled possition $position, positionOffset $positionOffset ")
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d("View pager","onPageSelected, position $position")
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                Log.d("View pager","onPageScrollStateChanged, state $state")
            }
        }
        )

        // создание верхних кнопок табов
        tabLayout = view.findViewById(R.id.tab_layout_universe)
        TabLayoutMediator(tabLayout,viewPager){tab,position-> val type = adapter.items[position]

/*
           when (position){
               1->R.drawable.ic_wikipedia
               2->R.drawable.ic_plus_fab
               3->R.drawable.ic_baseline_wine_bar_24
           }*/

            tab.text = type.name

            tab.icon = ContextCompat.getDrawable(requireContext(),R.drawable.ic_wikipedia)

        }.attach()

    }

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
        private var isMain = true
    }
}