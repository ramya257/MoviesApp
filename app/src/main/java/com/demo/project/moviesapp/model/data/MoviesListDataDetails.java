package com.demo.project.moviesapp.model.data;

/**
 * Created by ramya on 25/3/17.
 */

public class MoviesListDataDetails {
    private String Title;
    private String Year;
    private String imdbID;
    private String Type;
    private String Poster;


    public MoviesListDataDetails(String title, String year, String imdbID, String type, String poster) {
        this.Title = title;
        this.Year = year;
        this.imdbID = imdbID;
        this.Type = type;
        this.Poster = poster;
    }

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return Type;
    }

    public String getPoster() {
        return Poster;
    }


}
