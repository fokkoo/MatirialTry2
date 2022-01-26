package com.example.matirialtry2.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.matirialtry2.R

private const val pageTypeKey = "pageTypeKey"

class UniversePageFragment : Fragment() {

    private val universePageType by lazy {
        requireArguments().getSerializable(pageTypeKey) as UniversePageType
    }

    private lateinit var titleTextView: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_universe_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleTextView = view.findViewById(R.id.text_view_universe_page_titele)
        titleTextView.text = universePageType.name
    }

    companion object {


        fun newInstance(universePageType: UniversePageType): UniversePageFragment {
            val bundle = Bundle()
            bundle.putSerializable(pageTypeKey, universePageType)

            val fragment = UniversePageFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
