package com.demo.project.moviesapp.view;

import com.demo.project.moviesapp.model.data.MoviesListDataDetails;

import java.util.List;

/**
 * Created by ramya on 25/3/17.
 */

public interface MoviesView {
    void hideKeyboard();
    void showProgressBar(boolean show);
    void showError(String message);
    void setMoviesList(List<MoviesListDataDetails> moviesListDataDetailsList);
}
