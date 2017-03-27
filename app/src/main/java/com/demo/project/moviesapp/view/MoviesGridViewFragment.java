package com.demo.project.moviesapp.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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
 * {@link MoviesGridViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MoviesGridViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoviesGridViewFragment extends Fragment implements MoviesView {
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
    public static int page=0;
    private static String search_query;
    private Button button;

    private OnFragmentInteractionListener mListener;

    public MoviesGridViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoviesGridViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoviesGridViewFragment newInstance(String param1, String param2) {
        MoviesGridViewFragment fragment = new MoviesGridViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_movies_grid_view, container, false);
        progressBar=(ProgressBar)view.findViewById(R.id.progressBar);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
        moviesListAdapter= new MoviesListAdapter(getContext(), 1);
        button=(Button)view.findViewById(R.id.load_more_button);
        button.setVisibility(View.INVISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestMovies(search_query);
            }
        });
        initialise();
        return view;
    }
    public void requestMovies(String query)
    {
        search_query=query;
        page=page+1;
        moviesListPresenter.getMoviesList(query,page);
    }
    public void clearPageNo()
    {
       button.setVisibility(View.INVISIBLE);
        moviesListAdapter.removeList();
        page=0;
        Log.d("page no is",String.valueOf(page));
    }

    private void initialise() {
        moviesListPresenter= new MoviesListPresenterImpl(this,new RetrofitMoviesListProvider());
        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
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
        button.setVisibility(View.VISIBLE);
        if (moviesListDataDetailsList.size() == 0) {
            moviesListAdapter.setMoviesListDataDetailsList(moviesListDataDetailsList);
            moviesListAdapter.notifyDataSetChanged();
        } else {
            moviesListAdapter.addList(moviesListDataDetailsList);
            moviesListAdapter.notifyDataSetChanged();
        }
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
