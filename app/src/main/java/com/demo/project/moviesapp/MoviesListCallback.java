package com.demo.project.moviesapp;

import com.demo.project.moviesapp.model.data.MoviesListData;

/**
 * Created by ramya on 25/3/17.
 */

public interface MoviesListCallback {
    void onSuccess(MoviesListData moviesListData);
    void onFailure(String message);
}
