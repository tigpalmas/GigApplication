package com.example.tiago.myapplication.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.tiago.myapplication.EventDetailActivity
import com.example.tiago.myapplication.R
import com.example.tiago.myapplication.domain.Event
import com.example.tiago.myapplication.utils.Util
import com.squareup.picasso.Picasso

/**
 * Created by tiago on 17/12/2017.
 */
class EventAdapter(
        items: List<Event>,
        ctx: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list = items;
    var context = ctx;

    val EVENT = 1
    val LAST = 0


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        var item = list.get(position);
        when (holder?.itemViewType) {
            EVENT -> {
                val vh1 = holder as ViewHolder
                vh1.bind(item, context);
            }
            LAST -> {
                val vh2 = holder as ViewHolderLast
                vh2.bind(item, context)
            }

        }
    }

    override fun getItemCount(): Int {

      return list.size
    }

    override fun getItemViewType(position: Int): Int {

            return EVENT


    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent?.getContext())
        when (viewType) {
            EVENT -> {
                val v1 = inflater.inflate(R.layout.item_event, parent, false)
                viewHolder = ViewHolder(v1)
            }
            else -> {
                val v3 = inflater.inflate(R.layout.item_last, parent, false)
                viewHolder = ViewHolderLast(v3)
            }
        }
        return viewHolder
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var ctx : Context
        lateinit var event: Event;
        val imgEvent = itemView?.findViewById<ImageView>(R.id.iv_bonus);
        val imgEstablishment = itemView?.findViewById<ImageView>(R.id.img_establishment);


        val txtEventName = itemView?.findViewById<TextView>(R.id.txt_event_name);
        val txtMonth = itemView?.findViewById<TextView>(R.id.txt_month);
        val txtDay = itemView?.findViewById<TextView>(R.id.txt_day);
      //  val txtDate = itemView?.findViewById<TextView>(R.id.txt_date);

        init {
            itemView?.setOnClickListener(this)
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

    class ViewHolderLast(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        lateinit var ctx: Context


        init {
            itemView?.setOnClickListener(this)
        }

        fun bind(item: Event, ctx: Context){
            this.ctx = ctx;

        }

        override fun onClick(p0: View?) {
            Toast.makeText(ctx, "Aguarde...", Toast.LENGTH_LONG).show();
        }
    }
}