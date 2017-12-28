package com.example.tiago.myapplication.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.tiago.myapplication.R
import com.example.tiago.myapplication.adapters.EstablishmentsAdapter
import com.example.tiago.myapplication.adapters.EventAdapter
import com.example.tiago.myapplication.domain.Establishment
import com.example.tiago.myapplication.domain.Event
import com.example.tiago.myapplication.model.MVP
import com.example.tiago.myapplication.presenter.PresenterListEstablishment
import com.example.tiago.myapplication.presenter.PresenterListEvent
import kotlinx.android.synthetic.main.fragment_list_establishment.*


/**
 * A simple [Fragment] subclass.
 */
class EventsFragment : Fragment(), MVP.ViewListEvents {
    var presenter: MVP.PresenterListEvents? = null
    private var mEstablishment: Establishment? = null;
    var adapter : EventAdapter? = null;

    companion object {
        val EXTRA_ESTABLISMENT = "extra_establishment"

        fun novaInstancia(establishment: Establishment): EventsFragment {
            val parametros = Bundle()
            parametros.putSerializable(EXTRA_ESTABLISMENT, establishment)
            val fragment = EventsFragment()
            fragment.arguments = parametros
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
        mEstablishment = arguments?.getSerializable(EXTRA_ESTABLISMENT) as? Establishment


    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_events, container, false)
        presenter = PresenterListEvent()
        presenter?.setView(this);
        if(mEstablishment!=null){
            presenter?.retrieveEvents(mEstablishment!!._id)
        }

        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showLoadProgresss(true, "Buscando Programação");
        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {  swipeRefreshLayout?.setRefreshing(false)})
        var activity = activity;
        if(activity !=null){
            rv_events?.layoutManager = LinearLayoutManager(activity,  LinearLayoutManager.VERTICAL, false)
            rv_events.setHasFixedSize(true);
            adapter = EventAdapter(presenter?.getEvents()!!, activity);
            rv_events.adapter = adapter;
        }
    }

    override fun updateList() {

    }

    override fun showLoadProgresss(status: Boolean, message: String) {
        if (status) {
            swipeRefreshLayout?.setRefreshing(true)
        } else {
            swipeRefreshLayout?.setRefreshing(false)
        }
        txt_loading_events?.setText(message)
    }


}// Required empty public constructor
