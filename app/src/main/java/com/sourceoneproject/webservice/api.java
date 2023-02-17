package com.sourceoneproject.webservice;
import com.sourceoneproject.modelclass.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface api {
    public String APIKEY = "909594533c98883408adef5d56143539";

    @GET("day?api_key="+APIKEY)//endpoint
    Call<MovieModel> getLatestMovie();

    @GET("week?api_key="+APIKEY)//endpoint
    Call<MovieModel> getPopularMovie();
}
