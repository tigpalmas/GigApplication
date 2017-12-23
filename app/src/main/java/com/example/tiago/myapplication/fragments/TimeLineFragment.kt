package com.example.tiago.myapplication.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.tiago.myapplication.R

import com.example.tiago.myapplication.adapters.TimeLineAdapter
import com.example.tiago.myapplication.model.MVP
import com.example.tiago.myapplication.presenter.PresenterTimeline
import kotlinx.android.synthetic.main.fragment_list_establishment.*
import kotlinx.android.synthetic.main.fragment_time_line.*


/**
 * A simple [Fragment] subclass.
 */
class TimeLineFragment : Fragment(), MVP.ViewListTimeLine {
    var presenter: MVP.PresenterListTimeLine? = null
    var adapter : TimeLineAdapter? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_time_line, container, false)
        presenter = PresenterTimeline()
        presenter?.setView(this)
        presenter?.retriveData()

        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        srl_timeline.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {  srl_timeline?.setRefreshing(false)})
        var activity = activity;
        if(activity !=null){
            rv_objects!!.layoutManager = LinearLayoutManager(activity,  LinearLayoutManager.VERTICAL, false)
            rv_objects.setHasFixedSize(true);
            adapter = TimeLineAdapter(presenter?.getObjects()!!, activity);
            rv_objects.adapter = adapter;
        }
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showLoadProgresss(status: Boolean, message: String) {
        if (status) {
            srl_timeline?.setRefreshing(true)
        } else {
            srl_timeline?.setRefreshing(false)
        }
        txt_timeline?.setText(message)
    }


}// Required empty public constructor
