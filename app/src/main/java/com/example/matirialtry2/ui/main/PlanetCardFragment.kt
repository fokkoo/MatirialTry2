package com.example.matirialtry2.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import coil.api.load
import com.example.matirialtry2.PurchaseDetailFragment
import com.example.matirialtry2.R


class PlanetCardFragment : Fragment() {

    private lateinit var expandedToolbarBackgroundImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_planet_card,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        expandedToolbarBackgroundImageView = view.findViewById(R.id.imageView2)
        val url = "http://images.vfl.ru/ii/1602851736/c36aa1e1/31959638.jpg"
        expandedToolbarBackgroundImageView.load(url)
    }


    companion object {
        fun newInstance() = PlanetCardFragment()
        private var isMain = true
    }
}