package com.example.tiago.myapplication.adapters

import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.tiago.myapplication.EventDetailActivity
import com.example.tiago.myapplication.R
import com.example.tiago.myapplication.R.id.txt_event_name
import com.example.tiago.myapplication.domain.Establishment
import com.example.tiago.myapplication.domain.Event
import com.example.tiago.myapplication.domain.TimelineModel
import com.example.tiago.myapplication.fragments.BaseEstablishmentFragment
import com.example.tiago.myapplication.fragments.EventDetailFragment
import com.example.tiago.myapplication.utils.Util
import com.squareup.picasso.Picasso

/**
 * Created by tiago on 17/12/2017.
 */
class EventAdapter(items: List<Event>, ctx: Context): RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    var list = items;
    var context = ctx;


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var item = list.get(position);
        holder?.bind(item, context);
    }

    override fun getItemCount(): Int {
      return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return EventAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_event, parent, false))
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var ctx : Context
        lateinit var event: Event;
        val imgEvent = itemView?.findViewById<ImageView>(R.id.iv_event);


        val txtEventName = itemView?.findViewById<TextView>(R.id.txt_event_name);
        val txtMonth = itemView?.findViewById<TextView>(R.id.txt_month);
        val txtDay = itemView?.findViewById<TextView>(R.id.txt_day);
      //  val txtDate = itemView?.findViewById<TextView>(R.id.txt_date);

        init {
            imgEvent?.setOnClickListener(this)
        }

        fun bind(item: Event, ctx: Context){
            this.ctx = ctx;
            event = item;
           // txtDate?.setText(Util.obterDataPorExtenso(item?.beginDate))
            txtEventName?.text = event?.name
            txtMonth?.text = Util.obterMonth(event?.beginDate).toUpperCase()
            txtDay?.text = Util.obterDay(event?.beginDate).toUpperCase()
            Picasso.with(ctx).load(item.imgUrl).into(imgEvent)

        }

        override fun onClick(p0: View?) {
            val intent = Intent(ctx, EventDetailActivity::class.java)
            intent.putExtra("eventExtra", event);
            ctx?.startActivity(intent)
        }
    }
}