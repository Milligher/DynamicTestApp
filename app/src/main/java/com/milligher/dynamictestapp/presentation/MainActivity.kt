package com.milligher.dynamictestapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.milligher.dynamictestapp.App
import com.milligher.dynamictestapp.R
import com.milligher.dynamictestapp.presentation.adapter.ViewPagerAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MainActivityViewModelFactory
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)

        viewModel.getConfiguration()
        viewModel.getConfigurationLiveDataItem().observe(this) {
            val viewPager = findViewById<ViewPager2>(R.id.view_pager)
            viewPager.adapter = ViewPagerAdapter(this, it)

            val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = (position + 1).toString()
            }.attach()
        }
    }
}