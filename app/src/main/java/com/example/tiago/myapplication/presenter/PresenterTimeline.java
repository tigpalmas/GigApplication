package com.example.tiago.myapplication.presenter;

import com.example.tiago.myapplication.domain.Bonus;
import com.example.tiago.myapplication.domain.Event;
import com.example.tiago.myapplication.domain.FilterTimeline;
import com.example.tiago.myapplication.domain.News;
import com.example.tiago.myapplication.domain.TimeLineLists;
import com.example.tiago.myapplication.domain.TimelineModel;

import com.example.tiago.myapplication.model.MVP;
import com.example.tiago.myapplication.model.ModelTimeline;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 23/12/2017.
 */

public class PresenterTimeline implements MVP.PresenterListTimeLine {
    private MVP.ModelListTimeLine model;
    private MVP.ViewListTimeLine view;
    private List<TimelineModel> mObjects = new ArrayList<>();
    private List<TimelineModel> filteredObjects = new ArrayList<>();
    private List<Event> mEvents = new ArrayList<>();
    private List<Bonus> mBonus = new ArrayList<>();
    private List<News> mNews = new ArrayList<>();

    private List<TimeLineLists> timeLineLists = new ArrayList<>();

    public PresenterTimeline() {
        model = new ModelTimeline(this);
    }





    @NotNull
    @Override
    public List<TimelineModel> getObjects() {
        return mObjects;
    }


    @NotNull
    @Override
    public List<Event> getEvents() {
        return mEvents;
    }

    @NotNull
    @Override
    public List<Bonus> getBonus() {
        return mBonus;
    }

    @NotNull
    @Override
    public List<News> getNews() {
        return mNews;
    }







    @Override
    public void setView(@NotNull MVP.ViewListTimeLine view) {
        this.view = view;
    }

    @Override
    public void showLoadProgresss(boolean status, @NotNull String message) {
        view.showLoadProgresss(status, message);
    }


    @Override
    public void retriveData(@NotNull FilterTimeline filterTimeline) {
        model.retriveData(filterTimeline);
    }

    @Override
    public void retriveEventsData() {
        model.retriveEventsData();
    }

    @Override
    public void retriveBonusData() {
        model.retriveBonusData();
    }

    @Override
    public void retriveNewsData() {
        model.retriveNewsData();
    }

    @Override
    public void clearList() {
        mObjects.clear();
        updateList(mObjects);
    }

    @Override
    public void updateList(@NotNull List<TimelineModel> objects) {
      //  mObjects.addAll(objects);

        for(TimelineModel model : objects){
            if(model.getEventId()!=null){
                model.setCategory("Eventos");
            }  if(model.getBonusId()!=null){
                model.setCategory("Bônus");
            }
            if(model.getNewsId()!=null){
                model.setCategory("Noticías");
            }
        }




        final List<String> categories = new ArrayList<>();
        for (TimelineModel object : objects) {
            if (!categories.contains(object.getCategory())) {
                categories.add(object.getCategory());
            }
        }





        for (int i = 0; i < categories.size(); i++) {
            List<TimelineModel> list = new ArrayList<TimelineModel>();
            for (TimelineModel object : objects) {
                if (object.getCategory().equals(categories.get(i))) {
                    list.add(object);
                }
            }
            TimeLineLists genre = new TimeLineLists(categories.get(i), list);
            timeLineLists.add(genre);
        }

        view.updateList();
    }


    @Override
    public void updateListEvents(@NotNull List<Event> objects) {
        mEvents.addAll(objects);
        view.updateListEvents();
    }


    @Override
    public void updateListBonus(@NotNull List<Bonus> objects) {
       mBonus.addAll(objects);


        view.updateListBonus();
    }

    @Override
    public void updateListNews(@NotNull List<News> objects) {
        mNews.addAll(objects);
        view.updateListNews();
    }


    @NotNull
    @Override
    public List<TimeLineLists> getTimeLineList() {
        return this.timeLineLists;
    }
}
