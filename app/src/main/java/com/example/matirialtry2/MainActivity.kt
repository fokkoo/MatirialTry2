package com.example.matirialtry2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.matirialtry2.ui.main.MainFragment
import com.example.matirialtry2.ui.main.PictureOfTheDayFragment
import com.example.matirialtry2.ui.main.UniverseFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // setTheme(DailyImageFragment.currentTheme)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
           supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<UniverseFragment>(R.id.fragment_container)
            }

/*
           supportFragmentManager.beginTransaction()
               .replace(R.id.container, UniverseFragment.newInstance())
               .commitNow()

 */
       }
   }
}