package com.example.tugasarvy11rpl1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailMovieFav extends AppCompatActivity{
    Realm realm;
    RealmHelper realmHelper;
    ModelMovieRealm movieModel;

    Bundle extras;
    String title;
    String date;
    String deskripsi;
    String path;

    TextView tvjudul;
    ImageView ivposter;
    TextView tvdesc;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detail_movie_fav );
        extras = getIntent().getExtras();
        tvjudul = (TextView) findViewById( R.id.tvjudul );
        tvdesc = (TextView) findViewById( R.id.txtdeskripsi );
        ivposter = (ImageView) findViewById( R.id.ivposter );

        if (extras != null) {
            title = extras.getString("judul");
            id = extras.getString("id");
            date = extras.getString("date");
            deskripsi = extras.getString("deskripsi");
            path = extras.getString("path");
            tvjudul.setText( title );
            tvdesc.setText( deskripsi );
            Glide.with( DetailMovieFav.this )
                    .load( path )
                    .override( Target.SIZE_ORIGINAL )
                    .placeholder( R.mipmap.ic_launcher )
                    .into( ivposter );
            // and get whatever type user account id is
        }
        Realm.init(DetailMovieFav.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

    }
}