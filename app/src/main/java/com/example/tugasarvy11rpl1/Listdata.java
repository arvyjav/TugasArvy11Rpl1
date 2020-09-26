package com.example.tugasarvy11rpl1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Listdata extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private ArrayList<Model> DataArrayList; //kit add kan ke main_menu
    private ImageView tambah_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdata);
        getSupportActionBar().hide();
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        //addData();
        addDataOnline();
    }

//    private void addData() {
//        //offline, isi data offline dulu
//        DataArrayList = new ArrayList<>();
//        Model data1 = new Model();
//        data1.setOriginal_title("Judul Film");
//        data1.setPoster_path("https://image.tmdb.org/t/p/w500/uOw5JD8IlD546feZ6oxbIjvN66P.jpg");
//        data1.setAdult(false);
//        data1.setOverview("Deskripsi Film disini");
//        data1.setVote_count(134);
//        data1.setRelease_date("01-01-2020");
//        DataArrayList.add(data1);
//
//        adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
//            @Override
//            public void onClick(int position) {
//                Model movie =  DataArrayList.get(position);
//                Intent intent = new Intent(getApplicationContext(), DetailMovie.class);
//                intent.putExtra("judul", movie.original_title);
//                intent.putExtra("date", movie.release_date);
//                intent.putExtra("deskripsi", movie.overview);
//                intent.putExtra("path", movie.poster_path);
//                startActivity(intent);
//                Toast.makeText(ListData.this, "" + position, Toast.LENGTH_LONG);
//
//            }
//
//            @Override
//            public void test() {
//
//            }
//        });
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListData.this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
//
//    }

    void addDataOnline(){
        //data online
        AndroidNetworking.get("https://api.themoviedb.org/3/movie/now_playing?api_key=6ac7a042ac3b7599a689eb943fa0b6d0&language=en-US")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("hasiljson", "onResponse: " + response.toString());
                        //jika sudah berhasil debugm lanjutkan code dibawah ini
                        DataArrayList = new ArrayList<>();
                        Model modelku;
                        try {
                            Log.d("hasiljson", "onResponse: " + response.toString());
                            JSONArray jsonArray = response.getJSONArray("results");
                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                modelku = new Model();
                                modelku.setId(jsonObject.getInt("id"));
                                modelku.setOriginal_title(jsonObject.getString("original_title"));
                                modelku.setRelease_date(jsonObject.getString("release_date"));
                                modelku.setPoster_path("https://image.tmdb.org/t/p/w500"+jsonObject.getString("poster_path"));
                                modelku.setAdult(jsonObject.getBoolean("adult"));
                                modelku.setOverview(jsonObject.getString("overview"));
                                modelku.setVote_count(jsonObject.getInt("vote_count"));
                                DataArrayList.add(modelku);

                            }
                            //untuk handle click
                            adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
                                @Override
                                public void onClick(int position) {
                                    Model movie =  DataArrayList.get(position);
                                    Intent intent = new Intent(getApplicationContext(), DetailMovie.class);
                                    intent.putExtra("judul", movie.original_title);
                                    intent.putExtra("date", movie.release_date);
                                    intent.putExtra("deskripsi", movie.overview);
                                    intent.putExtra("path", movie.poster_path);
                                    startActivity(intent);
                                    Toast.makeText(Listdata.this, "" + position, Toast.LENGTH_LONG);
                                }

                                @Override
                                public void test() {

                                }
                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Listdata.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("errorku", "onError errorCode : " + error.getErrorCode());
                        Log.d("errorku", "onError errorBody : " + error.getErrorBody());
                        Log.d("errorku", "onError errorDetail : " + error.getErrorDetail());
                    }
                });
    }
}