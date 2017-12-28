package com.example.tiago.myapplication

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.example.tiago.myapplication.domain.Establishment
import com.example.tiago.myapplication.domain.Event
import com.example.tiago.myapplication.domain.TimelineModel
import kotlinx.android.synthetic.main.activity_event_detail.*

class EventDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)
        setSupportActionBar(toolbar)

        val item: TimelineModel? = intent?.getSerializableExtra("timelineExtra") as? TimelineModel;
        supportActionBar?.title = item?.eventId?.name
        if(item==null){
            val event: Event? = intent?.getSerializableExtra("eventExtra") as? Event;
            supportActionBar?.title = event?.name
        }
    }
}
