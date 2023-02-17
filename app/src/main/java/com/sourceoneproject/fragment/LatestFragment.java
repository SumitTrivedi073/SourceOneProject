package com.sourceoneproject.fragment;

import static com.sourceoneproject.utils.utility.isNetworkAvailable;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sourceoneproject.modelclass.MovieModel;
import com.sourceoneproject.R;
import com.sourceoneproject.webservice.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import com.sourceoneproject.adapter.MovieAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatestFragment extends Fragment {


    View view;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_latest, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);

        if (isNetworkAvailable(getActivity())) {
            getMovieList();
        }else {
            Toast.makeText(getActivity(),"Please check your internet Connection",Toast.LENGTH_LONG).show();

        }
        return view;
    }

    private void getMovieList() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Call<MovieModel> call = RetrofitClient.getInstance().getapi().getLatestMovie();

        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {

                if(progressDialog!=null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                }

                if (response.isSuccessful()) {
                    Log.e("response", String.valueOf(response.body()));

                    assert response.body() != null;
                    MovieModel movieModel = response.body();

                        List<MovieModel.Result> contentList = movieModel.getResults();

                        Log.e("contentList", String.valueOf(contentList.size()));
                        Log.e("contentList_Res", String.valueOf(contentList));

                    MovieAdapter  adapter = new MovieAdapter((Context) getActivity(), (ArrayList<MovieModel.Result>) contentList);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                Log.e("OnFailure",t.getMessage());
                if(progressDialog!=null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                getMovieList();

            }
        });

    }
}