package com.example.tiago.myapplication.presenter;

import com.example.tiago.myapplication.domain.Establishment;
import com.example.tiago.myapplication.domain.Event;
import com.example.tiago.myapplication.model.MVP;
import com.example.tiago.myapplication.model.ModelLIstEvents;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 17/12/2017.
 */

public class PresenterListEvent implements MVP.PresenterListEvents {
    private MVP.ModelListEvents model;
    private MVP.ViewListEvents view;
    private List<Event> mEvent = new ArrayList<>();

    public PresenterListEvent() {
        model = new ModelLIstEvents(this);
    }

    @Override
    public void retrieveEvents(@NotNull String id) {
        model.retrieveEvents(id);
    }

    @Override
    public void updateList(@NotNull List<Event> events) {
        mEvent.addAll(events);
        view.updateList();
    }

    @Override
    public void setView(@NotNull MVP.ViewListEvents view) {
        this.view = view;
    }

    @Override
    public void showLoadProgresss(boolean status, @NotNull String message) {
        view.showLoadProgresss(status, message);
    }

    @NotNull
    @Override
    public List<Event> getEvents() {
        return this.mEvent;
    }
}
