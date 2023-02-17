package com.sourceoneproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sourceoneproject.modelclass.MovieModel;
import com.sourceoneproject.R;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private ArrayList<MovieModel.Result> dataModelArrayList;
    Context mContext;

    public MovieAdapter(Context ctx, ArrayList<MovieModel.Result> dataModelArrayList) {
        this.mContext = ctx;
        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;

    }

    @NonNull
    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyViewHolder holder, int position) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
        Log.e("image","https://image.tmdb.org/t/p/w500" + dataModelArrayList.get(position).getBackdropPath());
        Glide.with(mContext).load("https://image.tmdb.org/t/p/w500" + dataModelArrayList.get(position).getBackdropPath()).apply(options).into(holder.movie_img);

        holder.movieName.setText(dataModelArrayList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView movie_img;
        TextView movieName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            movie_img = itemView.findViewById(R.id.movie_img);
            movieName = itemView.findViewById(R.id.movieName);

        }

    }

    public void updateList(ArrayList<MovieModel.Result> updateList) {
        dataModelArrayList = updateList;
        notifyDataSetChanged();
    }

}
