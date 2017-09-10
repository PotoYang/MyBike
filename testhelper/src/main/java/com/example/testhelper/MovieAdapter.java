package com.example.testhelper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

/**
 * Created by 71579 on 2016/11/24.
 */

public class MovieAdapter extends BaseAdapter {
    private ArrayList<Movie> movies;
    private Activity activity;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;

    private NetworkImageView movieImage;
    private TextView movieName, movieRating, movieYear;

    public MovieAdapter() {
    }

    public MovieAdapter(Activity activity, ArrayList<Movie> movies) {
        this.activity = activity;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null)
            view = inflater.inflate(R.layout.movie_list_item, null);
        if (imageLoader == null)
            imageLoader = ApplicationController.getInstant().getImageLoader();

        movieImage = (NetworkImageView) view.findViewById(R.id.movie_image);
        movieRating = (TextView) view.findViewById(R.id.movie_rating);
        movieName = (TextView) view.findViewById(R.id.movie_name);
        movieYear = (TextView) view.findViewById(R.id.movie_year);

        movieImage.setImageUrl(movies.get(i).getImage(), imageLoader);
        movieName.setText(movies.get(i).getName());
        movieRating.setText("Rating: " + movies.get(i).getRating());
        movieYear.setText((CharSequence) movies.get(i).getYear());

        return view;
    }
}
