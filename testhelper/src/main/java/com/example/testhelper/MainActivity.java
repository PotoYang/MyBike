package com.example.testhelper;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MovieAdapter adapter;

    private static final String url = "";

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView movieList = (ListView) findViewById(R.id.movie_list);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        ArrayList<Movie> movies = new ArrayList<>();

        fetchMovies();
        adapter = new MovieAdapter(MainActivity.this, movies);
        movieList.setAdapter(adapter);
    }

    private void fetchMovies() {
        JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hidePDialog();
                    }
                }, 1000);
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject object = jsonArray.getJSONObject(i);
                        Movie movie = new Movie();
                        movie.setImage(object.getString("image"));
                        movie.setName(object.getString("name"));
                        movie.setRating(object.getString("rating"));
                        movie.setYear(object.getString("year"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, "error:" + volleyError.getMessage());
                hidePDialog();
            }
        });

        ApplicationController.getInstant().addToRequestQueue(req);
    }

    public void hidePDialog() {
        if (pDialog != null)
            pDialog.dismiss();
        pDialog = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }
}
