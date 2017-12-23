package com.example.tiago.myapplication.presenter;

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

    public PresenterTimeline() {
        model = new ModelTimeline(this);
    }

    @Override
    public void retriveData() {
        model.retriveData();
    }

    @NotNull
    @Override
    public List<TimelineModel> getObjects() {
        return mObjects;
    }

    @Override
    public void updateList(@NotNull List<TimelineModel> objects) {
        mObjects.addAll(objects);
        view.updateList();
    }

    @Override
    public void setView(@NotNull MVP.ViewListTimeLine view) {
        this.view = view;
    }

    @Override
    public void showLoadProgresss(boolean status, @NotNull String message) {
        view.showLoadProgresss(status, message);
    }

}
