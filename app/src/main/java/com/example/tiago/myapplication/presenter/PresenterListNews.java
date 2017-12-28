package com.example.tiago.myapplication.presenter;

import com.example.tiago.myapplication.domain.Event;
import com.example.tiago.myapplication.domain.News;
import com.example.tiago.myapplication.model.MVP;
import com.example.tiago.myapplication.model.ModelLIstEvents;
import com.example.tiago.myapplication.model.ModelListNews;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 17/12/2017.
 */

public class PresenterListNews implements MVP.PresenterListNews {
    private MVP.ModelListNews model;
    private MVP.ViewListNews view;
    private List<News> mNews = new ArrayList<>();

    public PresenterListNews() {
        model = new ModelListNews(this);
    }

    @Override
    public void retrieveNews(@NotNull String id) {
        model.retrieveNews(id);
    }

    @Override
    public void updateList(@NotNull List<News> news) {
        mNews.addAll(news);
        view.updateList();
    }

    @Override
    public void setView(@NotNull MVP.ViewListNews view) {
        this.view = view;
    }

    @Override
    public void showLoadProgresss(boolean status, @NotNull String message) {
        view.showLoadProgresss(status, message);
    }

    @NotNull
    @Override
    public List<News> getNews() {
        return this.mNews;
    }
}
