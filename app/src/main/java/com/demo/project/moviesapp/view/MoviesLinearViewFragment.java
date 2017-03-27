package com.demo.project.moviesapp.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.demo.project.moviesapp.R;
import com.demo.project.moviesapp.model.MoviesListProvider;
import com.demo.project.moviesapp.model.RetrofitMoviesListProvider;
import com.demo.project.moviesapp.model.data.MoviesListDataDetails;
import com.demo.project.moviesapp.presenter.MoviesListPresenter;
import com.demo.project.moviesapp.presenter.MoviesListPresenterImpl;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MoviesLinearViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MoviesLinearViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoviesLinearViewFragment extends Fragment implements MoviesView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MoviesListProvider moviesListProvider;
    private MoviesListAdapter moviesListAdapter;
    private MoviesListPresenter moviesListPresenter;
    private static int page=0;


    private OnFragmentInteractionListener mListener;

    public MoviesLinearViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     //* @param param2 Parameter 2.
     * @return A new instance of fragment MoviesLinearViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoviesLinearViewFragment newInstance(String param1) {
        MoviesLinearViewFragment fragment = new MoviesLinearViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies_linear_view, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        moviesListAdapter = new MoviesListAdapter(getContext(), 0);
        initialise();
        return view;
    }
    public void requestMovies(String query)
    {
        moviesListPresenter.getMoviesList(query,1);
    }

    private void initialise() {
        moviesListPresenter = new MoviesListPresenterImpl(this, new RetrofitMoviesListProvider());
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(moviesListAdapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void hideKeyboard() {
            View view = getActivity().getCurrentFocus();
            if (view != null) {
                InputMethodManager imm=(InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
    }

    @Override
    public void showProgressBar(boolean show) {
        if(show)
        {
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setMoviesList(List<MoviesListDataDetails> moviesListDataDetailsList) {
        moviesListAdapter.setMoviesListDataDetailsList(moviesListDataDetailsList);
        moviesListAdapter.notifyDataSetChanged();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
