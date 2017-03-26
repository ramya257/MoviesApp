package com.demo.project.moviesapp.model;

import com.demo.project.moviesapp.MoviesListCallback;

/**
 * Created by ramya on 25/3/17.
 */

public interface MoviesListProvider {
    void getMovies(String search_query, int page, MoviesListCallback moviesListCallback);
}
