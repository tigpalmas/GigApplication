package com.example.tiago.myapplication.model

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.tiago.myapplication.domain.*

/**
 * Created by tiago on 09/12/2017.
 */
interface MVP {

    //LOGIN
    interface  ModelLogin{
         fun loginFacebook()
         fun getActivity(): Activity
         fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent)
         fun loginServer( user: User)
         fun showProgressbar(status: Boolean, message: String)
         fun showToast(message: String)

    }

    interface PresenterLogin{
         fun loginFacebook()
         fun getContext(): Context
         fun getActivity(): Activity
         fun setView(view: MVP.ViewLogin)
         fun saveUserPref()
         fun checkUserAuthorizad(): Boolean
         fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent)
        fun showProgressbar(status: Boolean, message: String)
        fun showToast(message: String)
    }

    interface ViewLogin{
        fun showProgressbar(status: Boolean, message: String)
        fun showToast(message: String)

    }

    //TIMELINE

    interface ModelListTimeLine{
        fun retriveData(model: FilterTimeline);
        fun retriveEventsData();
        fun retriveBonusData();
        fun retriveNewsData();
    }

    interface PresenterListTimeLine{
        fun retriveData(filterTimeline: FilterTimeline);
        fun retriveEventsData();
        fun retriveBonusData();
        fun retriveNewsData();

         fun updateList(objects : List<TimelineModel>);
         fun updateListEvents(objects : List<Event>);
         fun updateListBonus(objects : List<Bonus>);
         fun updateListNews(objects : List<News>);
        fun setView(view: MVP.ViewListTimeLine);
        fun showLoadProgresss(status: Boolean, message: String);
         fun getObjects(): List<TimelineModel>
         fun getEvents(): List<Event>
         fun getBonus(): List<Bonus>
         fun getNews(): List<News>
         fun getTimeLineList(): List<TimeLineLists>
        fun clearList()

    }

    interface  ViewListTimeLine{
        fun updateList();
        fun updateListEvents();
        fun updateListBonus();
        fun updateListNews();
        fun showLoadProgresss(status: Boolean, message: String)
    }


    //LIST ESTABLISHMENT
    interface ModelListEstablishments{
        fun retrieveEstablishments();
    }

    interface PresenterListEstablishments{
        fun retrieveEstablishments();
        fun updateList(establishments : List<Establishment>);
        fun setView(view: MVP.ViewListEstablishment);
        fun showLoadProgresss(status: Boolean, message: String);
        fun buscar(s:String?);
        fun limparBusca();
        fun getEstablishment(): List<Establishment>
    }

    interface  ViewListEstablishment{
        fun updateList();
        fun showLoadProgresss(status: Boolean, message: String)
        fun filterEstablishmenn (establishments : List<Establishment>);
    }


    //List Events
    interface ModelListEvents{
        fun retrieveEvents(id: String);
    }

    interface PresenterListEvents{
        fun retrieveEvents(id: String);
        fun updateList(events : List<Event>);
        fun setView(view: MVP.ViewListEvents);
        fun showLoadProgresss(status: Boolean, message: String);
        fun getEvents(): List<Event>
    }

    interface  ViewListEvents{
        fun updateList();
        fun showLoadProgresss(status: Boolean, message: String)
    }

    //List News
    interface ModelListNews{
        fun retrieveNews(id: String);
    }

    interface PresenterListNews{
        fun retrieveNews(id: String);
        fun updateList(events : List<News>);
        fun setView(view: MVP.ViewListNews);
        fun showLoadProgresss(status: Boolean, message: String);
        fun getNews(): List<News>
    }

    interface  ViewListNews{
        fun updateList();
        fun showLoadProgresss(status: Boolean, message: String)
    }

    //List Bonus

    interface ModelListBonus{
        fun retrieveBonus(id: String);
    }

    interface PresenterListBonus{
        fun retrieveBonus(id: String);
        fun updateList(bonus : List<Bonus>);
        fun setView(view: MVP.ViewListBonus);
        fun showLoadProgresss(status: Boolean, message: String);
        fun getBonus(): List<Bonus>
    }

    interface  ViewListBonus{
        fun updateList();
        fun showLoadProgresss(status: Boolean, message: String)
    }
}