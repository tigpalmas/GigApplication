package com.example.tiago.myapplication.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.tiago.myapplication.R
import com.example.tiago.myapplication.adapters.BonusAdapter
import com.example.tiago.myapplication.domain.Establishment
import com.example.tiago.myapplication.model.MVP
import com.example.tiago.myapplication.presenter.PresenterListBonus
import kotlinx.android.synthetic.main.fragment_bonus.*


/**
 * A simple [Fragment] subclass.
 */
class BonusFragment : Fragment(), MVP.ViewListBonus {



    var presenter: MVP.PresenterListBonus? = null
    private var mEstablishment: Establishment? = null;
    var adapter : BonusAdapter? = null;

    companion object {
        val EXTRA_ESTABLISHMENT = "extra_establishment"

        fun novaInstancia(establishment: Establishment): BonusFragment {
            val parametros = Bundle()
            parametros.putSerializable(EXTRA_ESTABLISHMENT, establishment)
            val fragment = BonusFragment()
            fragment.arguments = parametros
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
        mEstablishment = arguments?.getSerializable(EventsFragment.EXTRA_ESTABLISMENT) as? Establishment
    }



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater!!.inflate(R.layout.fragment_bonus, container, false)
        presenter = PresenterListBonus()
        presenter?.setView(this);
        if(mEstablishment!=null){
            presenter?.retrieveBonus(mEstablishment!!._id)
        }else{
            presenter?.retrieveBonus("dfasdfasd")
        }
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showLoadProgresss(true, "Buscando Bônus");
        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {  swipeRefreshLayout?.setRefreshing(false)})
        var activity = activity;
        if(activity !=null){
            rv_objects!!.layoutManager = LinearLayoutManager(activity,  LinearLayoutManager.VERTICAL, false)
            rv_objects.setHasFixedSize(true);
            adapter = BonusAdapter( presenter?.getBonus()!!,activity);
            rv_objects.adapter = adapter;
        }
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showLoadProgresss(status: Boolean, message: String) {
        if (status) {
            swipeRefreshLayout?.setRefreshing(true)
        } else {
            swipeRefreshLayout?.setRefreshing(false)
        }
        txt_loading?.setText(message)
    }
}// Required empty public constructor
