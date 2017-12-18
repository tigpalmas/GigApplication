package com.example.tiago.myapplication.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.tiago.myapplication.R
import com.example.tiago.myapplication.domain.Establishment
import com.example.tiago.myapplication.domain.Event
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_blank_fragment2.*
import kotlinx.android.synthetic.main.item_event.*


/**
 * A simple [Fragment] subclass.
 */
class EventDetailFragment : Fragment() {

    private var mEvent: Event? = null;


    companion object {
        val EXTRA_EVENTO = "extra_establishment"

        fun novaInstancia(event: Event): EventDetailFragment {
            val parametros = Bundle()
            parametros.putSerializable(EXTRA_EVENTO, event)
            val fragment = EventDetailFragment()
            fragment.arguments = parametros
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
        mEvent = arguments?.getSerializable(EXTRA_EVENTO) as? Event


    }

     override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                     savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater!!.inflate(R.layout.fragment_event_detail, container, false)
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Picasso.with(activity).load(mEvent?.imgUrl).into(iv_event)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = mEvent?.name
    }

}// Required empty public constructor
