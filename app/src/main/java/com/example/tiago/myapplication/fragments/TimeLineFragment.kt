package com.example.tiago.myapplication.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import android.widget.Toast

import com.example.tiago.myapplication.R

import com.example.tiago.myapplication.adapters.TimeLineAdapter
import com.example.tiago.myapplication.domain.FilterTimeline
import com.example.tiago.myapplication.domain.TimelineModel
import com.example.tiago.myapplication.model.MVP
import com.example.tiago.myapplication.model.ModelTimeline
import com.example.tiago.myapplication.presenter.PresenterTimeline
import com.example.tiago.myapplication.utils.Util
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.fragment_list_establishment.*
import kotlinx.android.synthetic.main.fragment_time_line.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class TimeLineFragment : Fragment(), MVP.ViewListTimeLine,  DatePickerDialog.OnDateSetListener {
    override fun updateListBonus() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateListNews() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateListEvents() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    var presenter: MVP.PresenterListTimeLine? = null
    var adapter : TimeLineAdapter? = null;
     var timelineFilter =  FilterTimeline();


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
        presenter?.retriveData(timelineFilter)

        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showLoadProgresss(true, "Buscando Programação");
        srl_timeline.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {  srl_timeline?.setRefreshing(false)})
        var activity = activity;
        if(activity !=null){
            rv_objects!!.layoutManager = LinearLayoutManager(activity,  LinearLayoutManager.VERTICAL, false)
            rv_objects.setHasFixedSize(true);
            adapter = TimeLineAdapter(presenter?.getObjects()!!, activity);
            rv_objects.adapter = adapter;
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = "Timeline"
    }


    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        presenter?.clearList()
        showLoadProgresss(true, "Buscando para $dayOfMonth/$monthOfYear");
        val month = monthOfYear+1
        timelineFilter.date = "$month/$dayOfMonth/$year"
        presenter?.retriveData(timelineFilter);
    }

    fun showDateFilter(){
        val now = Calendar.getInstance()
        val dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        )
        dpd.show(activity.fragmentManager, "dateCalendarFilter")
    }


    override fun onPrepareOptionsMenu(menu: Menu?) {
        var menuCalendar = menu!!.findItem(R.id.action_calendar);
        menuCalendar?.isVisible = true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when(id){
            R.id.action_calendar ->{
                showDateFilter();
            }
        }
        return super.onOptionsItemSelected(item)
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
