package com.example.tiago.myapplication.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.tiago.myapplication.R
import com.example.tiago.myapplication.domain.Establishment
import com.example.tiago.myapplication.domain.Event
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

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val imgEvent = itemView?.findViewById<ImageView>(R.id.iv_event);

        fun bind(item: Event, ctx: Context){
            Log.i("teste", item.imgUrl);
            Picasso.with(ctx).load(item.imgUrl).into(imgEvent)
        }
    }
}