package com.demo.project.moviesapp.view;

import android.app.SearchManager;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.demo.project.moviesapp.R;

import butterknife.BindView;

import static java.security.AccessController.getContext;

public class MoviesActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tablayout;
    private MoviesViewPagerAdapter moviesViewPagerAdapter;
    public String search_query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
//        toolbar.setTitle("MoviesApp");
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        final SearchView searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                search_query = query;
                Bundle bundle = new Bundle();
                bundle.putString("query",search_query);
                MoviesLinearViewFragment moviesLinearViewFragment=MoviesLinearViewFragment.newInstance(search_query);
                MoviesGridViewFragment moviesGridViewFragment=MoviesGridViewFragment.newInstance(search_query,"");
                moviesLinearViewFragment.setArguments(bundle);
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);
        return true;
    }

}
