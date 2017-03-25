package com.demo.project.moviesapp.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.demo.project.moviesapp.R;

import butterknife.BindView;

public class MoviesActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    private Toolbar toolbar;
    @BindView(R.id.viewPager)
    private ViewPager viewPager;
    @BindView(R.id.tabs)
    private TabLayout tablayout;
    private MoviesViewPagerAdapter moviesViewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        toolbar.setTitle("MoviesApp");
        setSupportActionBar(toolbar);
        setupViewPager(viewPager);
        tablayout.setupWithViewPager(viewPager);
    }
    private void setupViewPager(ViewPager viewPager) {
        moviesViewPagerAdapter=new MoviesViewPagerAdapter(getSupportFragmentManager());
        MoviesListOneFragment moviesListFragment= MoviesListOneFragment.newInstance(moviesViewPagerAdapter.getItem())
        moviesViewPagerAdapter.addFragment(new MoviesListOneFragment(),"MoviesList1");
        moviesViewPagerAdapter.addFragment(new MoviesListTwoFragment(),"MoviesList2");
        viewPager.setAdapter(moviesViewPagerAdapter);
    }

}
