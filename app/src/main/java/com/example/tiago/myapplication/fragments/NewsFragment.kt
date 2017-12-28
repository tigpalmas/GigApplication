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
import com.example.tiago.myapplication.domain.Establishment
import com.example.tiago.myapplication.model.MVP
import com.example.tiago.myapplication.presenter.PresenterListEvent
import com.example.tiago.myapplication.presenter.PresenterListNews
import kotlinx.android.synthetic.main.fragment_bonus.*
import kotlinx.android.synthetic.main.fragment_list_establishment.*
import kotlinx.android.synthetic.main.fragment_new.*


/**
 * A simple [Fragment] subclass.
 */
class NewsFragment : Fragment(), MVP.ViewListNews {
    var presenter: MVP.PresenterListNews? = null
    private var mEstablishment: Establishment? = null;

    var adapter : NewsAdapter? = null;


    companion object {
        val EXTRA_ESTABLISHMENT = "extra_establishment"
        fun novaInstancia(establishment: Establishment): NewsFragment {
            val parametros = Bundle()
            parametros.putSerializable(EXTRA_ESTABLISHMENT, establishment)
            val fragment = NewsFragment()
            fragment.arguments = parametros
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
        mEstablishment = arguments?.getSerializable(EXTRA_ESTABLISHMENT) as? Establishment


    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater!!.inflate(R.layout.fragment_new, container, false)
        presenter = PresenterListNews()
        presenter?.setView(this);
        if(mEstablishment!=null){
            presenter?.retrieveNews(mEstablishment!!._id)
        }
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var activity = activity;
        if(activity !=null){
            rv_news!!.layoutManager = LinearLayoutManager(activity,  LinearLayoutManager.VERTICAL, false)
            rv_news.setHasFixedSize(true);
            adapter = NewsAdapter(presenter?.getNews()!!, activity);
            rv_news.adapter = adapter;
        }
    }

    override fun updateList() {
      adapter?.notifyDataSetChanged();
    }

    override fun showLoadProgresss(status: Boolean, message: String) {
        if (status) {
            sf_news?.setRefreshing(true)
        } else {
            sf_news?.setRefreshing(false)
        }
        txt_news?.setText(message)
    }

}// Required empty public constructor
