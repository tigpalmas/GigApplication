package com.example.tiago.myapplication.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*

import com.example.tiago.myapplication.R
import com.example.tiago.myapplication.adapters.EstablishmentsAdapter
import com.example.tiago.myapplication.domain.Establishment
import com.example.tiago.myapplication.model.MVP
import com.example.tiago.myapplication.presenter.PresenterListEstablishment
import kotlinx.android.synthetic.main.fragment_list_establishment.*


/**
 * A simple [Fragment] subclass.
 */
class ListEstablishmentFragment : Fragment(), MVP.ViewListEstablishment, SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener {


    var presenter : MVP.PresenterListEstablishments? = null
    var adapter : EstablishmentsAdapter? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val layout =  inflater!!.inflate(R.layout.fragment_list_establishment, container, false)
        presenter = PresenterListEstablishment()
        presenter?.setView(this);
        presenter?.retrieveEstablishments()

        return layout;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showLoadProgresss(true, "Buscando Estabelecimentos");
        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {  swipeRefreshLayout?.setRefreshing(false)})
        var activity = activity;
        if(activity !=null){
            rv_events!!.layoutManager = GridLayoutManager(activity, 3, LinearLayoutManager.VERTICAL, false)
            rv_events.setHasFixedSize(true);
            adapter = EstablishmentsAdapter(presenter?.getEstablishment()!!, activity);
            rv_events.adapter = adapter;
        }


    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        var menuMain = menu!!.findItem(R.id.action_main);
        menuMain!!.isVisible = true
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        var menu = menu
        // Do something that differs the Activity's menu here
        menu = menu

        val searchItem = menu!!.findItem(R.id.action_search)
        searchItem.isVisible = true
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.queryHint = "Buscar"
        MenuItemCompat.setOnActionExpandListener(searchItem, this)
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
        txt_loading_events?.setText(message)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.title = "Estabelecimentos"
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(s: String?): Boolean {
        presenter?.buscar(s)
        return false
    }

    override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
        return true
    }

    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
        presenter!!.limparBusca()
        return true
    }

    override fun filterEstablishmenn(establishments: List<Establishment>) {
        var activity = activity;
        if(activity !=null){
            adapter = EstablishmentsAdapter(establishments!!, activity);
            rv_events.adapter = adapter;
        }
    }





}
