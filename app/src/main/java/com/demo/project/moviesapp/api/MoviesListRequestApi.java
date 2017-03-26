package com.demo.project.moviesapp.api;

import com.demo.project.moviesapp.helper.Urls;
import com.demo.project.moviesapp.model.data.MoviesListData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ramya on 25/3/17.
 */

public interface MoviesListRequestApi {
    @GET("/")
    Call<MoviesListData>getMoviesList(@Query("s")String search_query,@Query("page") int page);

}
