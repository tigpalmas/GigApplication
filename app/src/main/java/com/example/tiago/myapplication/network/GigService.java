package com.example.tiago.myapplication.network;

import com.example.tiago.myapplication.domain.Bonus;
import com.example.tiago.myapplication.domain.Establishment;
import com.example.tiago.myapplication.domain.Event;
import com.example.tiago.myapplication.domain.FilterTimeline;
import com.example.tiago.myapplication.domain.News;
import com.example.tiago.myapplication.domain.TimelineModel;
import com.example.tiago.myapplication.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by tiago on 17/12/2017.
 */

public interface GigService {

    @POST("/register")
    Call<User> registerUser(@Body User user);

    @POST("/timeline/0/20")
    Call<List<TimelineModel>> getObjects(@Body FilterTimeline model);

    @GET("/establishment")
    Call<List<Establishment>> getEstablishments();

    @GET("/event/0/10")
    Call<List<Event>> getEventsLine();

    @GET("/bonus/0/10")
    Call<List<Bonus>> getBonusLine();

    @GET("/news/0/10")
    Call<List<News>> getNewsLine();

    @GET("/event/establishment/{id}")
    Call<List<Event>> getEvents(@Path("id") String id);

    @GET("/news/establishment/{id}")
    Call<List<News>> getNews(@Path("id") String id);

    @GET("/bonus/establishment/{id}")
    Call<List<Bonus>> getBonus(@Path("id") String id);
}
