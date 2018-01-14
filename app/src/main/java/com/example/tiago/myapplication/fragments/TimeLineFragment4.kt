package com.example.tiago.myapplication.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.tiago.myapplication.R
import com.example.tiago.myapplication.adapters.EstablishmentsAdapter
import com.example.tiago.myapplication.adapters.TimeLineAdapter
import com.example.tiago.myapplication.adapters.TimeLineAdapter3
import com.example.tiago.myapplication.domain.FilterTimeline
import com.example.tiago.myapplication.domain.TimelineModel
import com.example.tiago.myapplication.model.MVP
import com.example.tiago.myapplication.presenter.PresenterTimeline
import com.example.tiago.myapplication.utils.SpacesItemDecoration
import kotlinx.android.synthetic.main.fragment_time_line_fragment4.*


/**
 * A simple [Fragment] subclass.
 */
class TimeLineFragment4 : Fragment(), MVP.ViewListTimeLine{

    var adapter : TimeLineAdapter3? = null;
    var presenter: MVP.PresenterListTimeLine? = null


    public override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                     savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater!!.inflate(R.layout.fragment_time_line_fragment4, container, false)
        presenter = PresenterTimeline()
        presenter?.setView(this)
        presenter?.retriveData(FilterTimeline())
        return view;
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showLoadProgresss(true, "Buscando Estabelecimentos");
       swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {  swipeRefreshLayout?.setRefreshing(false)})
        var activity = activity;
        if(activity !=null){

        }


    }

    override fun updateList() {
        rv_objects?.layoutManager = LinearLayoutManager(activity,  LinearLayoutManager.VERTICAL, false)

        rv_objects?.addItemDecoration(SpacesItemDecoration(15))
        adapter = TimeLineAdapter3(presenter?.getTimeLineList()!!, activity);

        rv_objects.adapter = adapter;

    }

    override fun updateListEvents() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateListBonus() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateListNews() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoadProgresss(status: Boolean, message: String) {
        if (status) {
          swipeRefreshLayout?.setRefreshing(true)
        } else {
           swipeRefreshLayout?.setRefreshing(false)
        }
        txt_loading?.setText(message)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = "GiG YOU"

    }


}// Required empty public constructor
