package com.demo.project.moviesapp.presenter;

import com.demo.project.moviesapp.MoviesListCallback;
import com.demo.project.moviesapp.model.MoviesListProvider;
import com.demo.project.moviesapp.model.data.MoviesListData;
import com.demo.project.moviesapp.view.MoviesView;

/**
 * Created by ramya on 25/3/17.
 */

public class MoviesListPresenterImpl implements MoviesListPresenter {
    private MoviesView moviesView;
    private MoviesListProvider moviesListProvider;

    public MoviesListPresenterImpl(MoviesView moviesView, MoviesListProvider moviesListProvider) {
        this.moviesView = moviesView;
        this.moviesListProvider = moviesListProvider;
    }


    @Override
    public void getMoviesList(String search_query, int page) {
        moviesView.showProgressBar(true);
        moviesListProvider.getMovies(search_query, page, new MoviesListCallback() {
            @Override
            public void onSuccess(MoviesListData moviesListData) {
                if(moviesListData.isResponse())
                {
                    moviesView.hideKeyboard();
                    moviesView.setMoviesList(moviesListData.getSearch());
                    moviesView.showProgressBar(false);
                }
                else
                {
                   moviesView.showError(moviesListData.getError());
                }
                moviesView.showProgressBar(false);

            }

            @Override
            public void onFailure(String message) {
               moviesView.showError(message);
            }
        });

    }
}
