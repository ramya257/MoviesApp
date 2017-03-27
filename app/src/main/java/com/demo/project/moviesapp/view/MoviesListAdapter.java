package com.demo.project.moviesapp.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.demo.project.moviesapp.R;
import com.demo.project.moviesapp.model.data.MoviesListData;
import com.demo.project.moviesapp.model.data.MoviesListDataDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ramya on 25/3/17.
 */

public  class MoviesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int LINEAR_VIEW_TYPE=0;
    private static final int GRID_VIEW_TYPE=1;
    private MoviesView moviesView;
    private MoviesListData moviesListData;
    private List<MoviesListDataDetails> moviesListDataDetailsList=new ArrayList<>();
    private Context context;
    private int fragment_type;
    private LayoutInflater layoutInflater;
    public MoviesListAdapter(Context context,int fragment_type) {
        this.context = context;
        this.fragment_type = fragment_type;
        layoutInflater = LayoutInflater.from(context);
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 0) {
                View view = layoutInflater.inflate(R.layout.movies_list_linear_view_item, parent, false);
                return new MoviesListViewHolder(view);
            } else if (viewType == 1) {
                View view = layoutInflater.inflate(R.layout.movies_list_grid_view_item, parent, false);
                return new MoviesListViewHolder(view);
            }
        return null;
    }
    @Override
    public int getItemViewType(int position) {
        if(fragment_type==LINEAR_VIEW_TYPE)
        {
            return LINEAR_VIEW_TYPE;
        }
        else if(fragment_type==GRID_VIEW_TYPE)
        {
         return GRID_VIEW_TYPE;
        }
        return -999;
    }
    public void setMoviesListDataDetailsList(List<MoviesListDataDetails> moviesListDataDetailsList)
    {
        this.moviesListDataDetailsList=moviesListDataDetailsList;
    }
    public void addList(List<MoviesListDataDetails> moviesListDataDetailsList)
    {
       this.moviesListDataDetailsList.addAll(moviesListDataDetailsList);
        notifyDataSetChanged();
    }
    public void removeList()
    {
        this.moviesListDataDetailsList.clear();
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MoviesListDataDetails moviesListDataDetails=moviesListDataDetailsList.get(position);
        MoviesListViewHolder moviesListViewHolder=(MoviesListViewHolder)holder;
        Glide.with(context).load(moviesListDataDetails.getPoster()).into(moviesListViewHolder.movieImage);
        moviesListViewHolder.movieTitle.setText(moviesListDataDetails.getTitle());
        moviesListViewHolder.movieDescription.setText("Type:"+""+moviesListDataDetails.getType()+""
                +""+"Year"+""+moviesListDataDetails.getYear());
        moviesListViewHolder.movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),moviesListDataDetails.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesListDataDetailsList.size();

    }



    public class MoviesListViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView movieImage;
        private TextView movieTitle;
        private TextView movieDescription;

        public MoviesListViewHolder(View itemView) {
            super(itemView);
            movieImage=(ImageView)itemView.findViewById(R.id.moviePoster);
            movieTitle=(TextView)itemView.findViewById(R.id.movieTitle);
            movieDescription=(TextView)itemView.findViewById(R.id.movieDescription);
        }

    }
}
