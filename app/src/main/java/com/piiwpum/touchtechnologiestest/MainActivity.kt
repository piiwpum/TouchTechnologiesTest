package com.piiwpum.touchtechnologiestest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.piiwpum.touchtechnologiestest.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commit()
        }
    }
}