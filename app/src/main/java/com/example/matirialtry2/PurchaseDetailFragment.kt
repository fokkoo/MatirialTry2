package com.example.matirialtry2
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import coil.api.load
import com.example.matirialtry2.R
import com.example.matirialtry2.ui.main.PictureOfTheDayFragment

class PurchaseDetailFragment : Fragment() {

    private lateinit var expandedToolbarBackgroundImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_purchase_detail,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        expandedToolbarBackgroundImageView = view.findViewById(R.id.main_backdrop)
        val url = "http://images.vfl.ru/ii/1602851736/c36aa1e1/31959638.jpg"
        expandedToolbarBackgroundImageView.load(url)
    }


    companion object {
        fun newInstance() = PurchaseDetailFragment()
        private var isMain = true
    }
}