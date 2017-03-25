package com.demo.project.moviesapp.model.data;

import java.util.List;

/**
 * Created by ramya on 25/3/17.
 */

public class MoviesListData {
    private boolean Response;
    private String Error;
    private int totalResults;
    private List<MoviesListDataDetails> Search;

    public MoviesListData(boolean response, String error, int totalResults, List<MoviesListDataDetails> search) {
        this.Response = response;
        this.Error = error;
        this.totalResults = totalResults;
        this.Search = search;
    }

    public boolean isResponse() {
        return Response;
    }

    public void setResponse(boolean response) {
        Response = response;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<MoviesListDataDetails> getSearch() {
        return Search;
    }

    public void setSearch(List<MoviesListDataDetails> search) {
        Search = search;
    }
}
