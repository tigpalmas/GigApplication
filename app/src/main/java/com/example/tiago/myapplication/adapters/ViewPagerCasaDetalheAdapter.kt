package com.example.tiago.myapplication.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

import java.util.ArrayList

/**
 * Created by tiago on 21/04/2016.
 */
class ViewPagerCasaDetalheAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    internal var fragments = ArrayList<Fragment>()
    internal var tabTitles = ArrayList<String>()

    fun addFragments(fragment: Fragment, tabTitles: String) {
        this.fragments.add(fragment)
        this.tabTitles.add(tabTitles)
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabTitles[position]
    }


}
