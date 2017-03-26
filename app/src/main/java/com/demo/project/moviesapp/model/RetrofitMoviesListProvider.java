package com.demo.project.moviesapp.model;

import com.demo.project.moviesapp.MoviesListCallback;
import com.demo.project.moviesapp.api.MoviesListRequestApi;
import com.demo.project.moviesapp.helper.Urls;
import com.demo.project.moviesapp.model.data.MoviesListData;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ramya on 25/3/17.
 */

public class RetrofitMoviesListProvider implements MoviesListProvider{
    private String TAG="RetrofitMoviesList";
    private MoviesListRequestApi moviesListRequestApi;
    private Retrofit retrofit;
    public RetrofitMoviesListProvider()
    {

        HttpLoggingInterceptor httpLoggingInterceptor= new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
                .build();
        retrofit= new Retrofit.Builder().client(client).baseUrl(Urls.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();
    }
    @Override
    public void getMovies(String search_query, int page, final MoviesListCallback moviesListCallback) {
        moviesListRequestApi=retrofit.create(MoviesListRequestApi.class);
        Call<MoviesListData> call=moviesListRequestApi.getMoviesList(search_query,page);
        call.enqueue(new Callback<MoviesListData>() {
            @Override
            public void onResponse(Call<MoviesListData> call, Response<MoviesListData> response) {
                moviesListCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MoviesListData> call, Throwable t) {
                moviesListCallback.onFailure(t.getMessage());
            }
        });


    }
}
