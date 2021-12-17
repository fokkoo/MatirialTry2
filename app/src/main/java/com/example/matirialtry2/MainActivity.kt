package com.example.matirialtry2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.matirialtry2.ui.main.MainFragment
import com.example.matirialtry2.ui.main.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
    }
}