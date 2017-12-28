package com.example.tiago.myapplication.network;

import com.example.tiago.myapplication.domain.Establishment;
import com.example.tiago.myapplication.domain.Event;
import com.example.tiago.myapplication.domain.News;
import com.example.tiago.myapplication.domain.TimelineModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by tiago on 17/12/2017.
 */

public interface GigService {

    @GET("/timeline/0/10")
    Call<List<TimelineModel>> getObjects();

    @GET("/establishment")
    Call<List<Establishment>> getEstablishments();

    @GET("/event/establishment/{id}")
    Call<List<Event>> getEvents(@Path("id") String id);

    @GET("/news/establishment/{id}")
    Call<List<News>> getNews(@Path("id") String id);

    @GET("/bonus/establishment/{id}")
    Call<List<News>> getBonus(@Path("id") String id);
}
