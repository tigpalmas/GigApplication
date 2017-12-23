package com.example.tiago.myapplication.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.tiago.myapplication.R
import com.example.tiago.myapplication.domain.Event
import com.example.tiago.myapplication.domain.TimelineModel
import com.example.tiago.myapplication.utils.Util
import com.squareup.picasso.Picasso

/**
 * Created by tiago on 17/12/2017.
 */
class TimeLineAdapter(items: List<TimelineModel>, ctx: Context) : RecyclerView.Adapter<TimeLineAdapter.ViewHolder>() {
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
        return TimeLineAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_event, parent, false))
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var ctx: Context
        lateinit var event: Event;
        val imgEvent = itemView?.findViewById<ImageView>(R.id.iv_event);


        val txtEventName = itemView?.findViewById<TextView>(R.id.txt_event_name);
        //  val txtDate = itemView?.findViewById<TextView>(R.id.txt_date);

        init {
            imgEvent?.setOnClickListener(this)
        }

        fun bind(item: TimelineModel, ctx: Context) {
            this.ctx = ctx;
            event = item.eventId;


            // txtDate?.setText(Util.obterDataPorExtenso(item?.beginDate))
            txtEventName?.text = event.name
            Picasso.with(ctx).load(event.imgUrl).into(imgEvent)

        }

        override fun onClick(p0: View?) {
            /*  val fragmentTransaction = (ctx as FragmentActivity).supportFragmentManager.beginTransaction()
              val fragment = EventDetailFragment.novaInstancia(event);
              fragmentTransaction?.add(R.id.container, fragment, "event")
              fragmentTransaction?.addToBackStack(null)
              fragmentTransaction?.commit()*/

            Util.showToast(ctx, "Ainda não implementado")
        }
    }
}