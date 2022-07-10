package com.example.movielistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private RecyclerView recyclerView;
private RequestQueue requestQueue;
private List<Model> list = new ArrayList<>();;
String TAG = "MainActivity";
MovieAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        requestQueue = Volley.newRequestQueue(this);
        recyclerView = findViewById(R.id.rec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // requestQueue=VolleySingleton.getInstance(this).getRequestQueue();
        //fetchMovies();
        adapter = new MovieAdapter(MainActivity.this, list);
        recyclerView.setAdapter(adapter);
        callApi("https://run.mocky.io/v3/a4190b6d-4d93-4831-b3bc-31a959e31519");
    }
    private void callApi(String url){
        StringRequest request = new StringRequest(Request.Method.GET,url, response -> {
            Log.d(TAG, "callApi: "+ response);
            extract(response);
        }, error -> Log.d("error",error.toString()));
        requestQueue.add(request);
    }
    private void extract(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray movies = jsonObject.getJSONArray("movies");
            for (int i=0; i<movies.length(); i++){
                JSONObject childObj = movies.getJSONObject(i);
                String title = childObj.getString("title");
                String overview = childObj.getString("overview");
                String poster = childObj.getString("poster");
                Double rating = childObj.getDouble("rating");
                Model model = new Model(title, poster, overview, rating);
                list.add(model);
            }
            adapter.notifyDataSetChanged();
        }
        catch (Exception e){
            Log.d(TAG, "extract: "+ e.getMessage());
        }
    }
}