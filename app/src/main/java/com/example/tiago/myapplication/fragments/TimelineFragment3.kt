package com.example.tiago.myapplication.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.tiago.myapplication.R
import com.example.tiago.myapplication.adapters.ViewPagerCasaDetalheAdapter
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.fragment_timeline_fragment3.*


/**
 * A simple [Fragment] subclass.
 */
class TimelineFragment3 : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_timeline_fragment3, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var viewPagerCasaDetalheAdapter = ViewPagerCasaDetalheAdapter(childFragmentManager);
        viewPagerCasaDetalheAdapter.addFragments(EventsFragment(), "Eventos");
        viewPagerCasaDetalheAdapter.addFragments(BonusFragment(), "Bônus");
        viewPagerCasaDetalheAdapter.addFragments(NewsFragment(), "Notícias");




        viewPager?.offscreenPageLimit = 3
        viewPager?.setAdapter(viewPagerCasaDetalheAdapter);
        tabs.setupWithViewPager(viewPager);
    }



}// Required empty public constructor
