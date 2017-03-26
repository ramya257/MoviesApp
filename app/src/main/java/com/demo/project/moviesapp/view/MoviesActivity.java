package com.demo.project.moviesapp.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.demo.project.moviesapp.R;

import butterknife.BindView;

public class MoviesActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tablayout;
    private MoviesViewPagerAdapter moviesViewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
//        toolbar.setTitle("MoviesApp");
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("MoviesApp");
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);
        tablayout=(TabLayout)findViewById(R.id.tabs);
        tablayout.setupWithViewPager(viewPager);
    }
    private void setupViewPager(ViewPager viewPager) {
        moviesViewPagerAdapter=new MoviesViewPagerAdapter(getSupportFragmentManager());
        moviesViewPagerAdapter.addFragment(new MoviesLinearViewFragment(),"MoviesLinearList");
        moviesViewPagerAdapter.addFragment(new MoviesGridViewFragment(),"MoviesGridList");
        viewPager.setAdapter(moviesViewPagerAdapter);
    }

}
