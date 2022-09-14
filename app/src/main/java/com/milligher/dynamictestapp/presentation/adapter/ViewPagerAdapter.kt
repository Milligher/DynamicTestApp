package com.milligher.dynamictestapp.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.milligher.dynamictestapp.domain.model.configuration.Configuration
import com.milligher.dynamictestapp.presentation.fragment.DynamicFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity, private val configuration: Configuration): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return configuration.activities.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragmentList: MutableList<DynamicFragment> = mutableListOf()
        for (i in 1..configuration.activities.size){
            fragmentList.add(DynamicFragment.newInstance(conf = configuration))
        }
        return fragmentList[position]
    }

}