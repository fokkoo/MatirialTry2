package com.example.matirialtry2.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.matirialtry2.PurchaseDetailFragment
import com.example.matirialtry2.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class UniverseFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    private lateinit var fragmentContainerView: View
    private lateinit var bottomNavigationView: BottomNavigationView

    private val navigationItemSelectedListener = NavigationBarView.OnItemSelectedListener { item ->
        //this logic will be move into view model
        val fragment = when (item.itemId) {
            R.id.bottom_view_sun -> UniversePageFragment.newInstance(UniversePageType.Sun)

            R.id.bottom_view_venera -> PlanetCardFragment.newInstance()

            R.id.bottom_view_mercury -> PurchaseDetailFragment.newInstance()
            else -> throw IllegalArgumentException("Select unknown item")

        }

        openScreen(fragment)
        true
    }


    private val navigationItemReselectedListener =
        NavigationBarView.OnItemReselectedListener { item ->

        }

    private val backPressedReturnMainCallback = object : OnBackPressedCallback(true) {

        override fun handleOnBackPressed() {

            openScreen(UniversePageFragment.newInstance(UniversePageType.Earth))
            bottomNavigationView.selectedItemId = R.id.bottom_view_sun
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(backPressedReturnMainCallback)

        openScreen(UniversePageFragment.newInstance(UniversePageType.Earth))
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_universe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentContainerView = view.findViewById(R.id.view_fragment_container)

        bottomNavigationView = view.findViewById(R.id.navigation_view_main)
        bottomNavigationView.setOnItemSelectedListener(navigationItemSelectedListener)
        bottomNavigationView.setOnItemReselectedListener(navigationItemReselectedListener)



        viewPager = view.findViewById(R.id.view_pager_universe)
        val adapter = UniverseStatePagerAdapter(this)

        adapter.items = UniversePageType.values().toList()
        viewPager.adapter = adapter

        viewPager.setPageTransformer(ZoomOutPageTransformer())

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                Log.d(
                    "View pager",
                    " onPageScrolled possition $position, positionOffset $positionOffset "
                )
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d("View pager", "onPageSelected, position $position")
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                Log.d("View pager", "onPageScrollStateChanged, state $state")
            }
        }
        )

        // создание верхних кнопок табов
        tabLayout = view.findViewById(R.id.tab_layout_universe)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val type = adapter.items[position]



            tab.text = type.name

            tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_wikipedia)

        }.attach()

    }

    private fun openScreen(fragment: Fragment) {
        childFragmentManager.beginTransaction().apply {
            replace(R.id.view_fragment_container, fragment, fragment::class.java.simpleName)
            commit()
        }
    }


}