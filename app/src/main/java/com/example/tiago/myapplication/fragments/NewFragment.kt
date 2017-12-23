package com.example.tiago.myapplication.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.tiago.myapplication.R
import com.example.tiago.myapplication.adapters.BonusAdapter
import com.example.tiago.myapplication.adapters.NewsAdapter
import kotlinx.android.synthetic.main.fragment_bonus.*
import kotlinx.android.synthetic.main.fragment_new.*


/**
 * A simple [Fragment] subclass.
 */
class NewFragment : Fragment() {

    var adapter : NewsAdapter? = null;

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_new, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var activity = activity;
        if(activity !=null){
            rv_news!!.layoutManager = LinearLayoutManager(activity,  LinearLayoutManager.VERTICAL, false)
            rv_news.setHasFixedSize(true);
            adapter = NewsAdapter( activity);
            rv_news.adapter = adapter;
        }
    }

}// Required empty public constructor
