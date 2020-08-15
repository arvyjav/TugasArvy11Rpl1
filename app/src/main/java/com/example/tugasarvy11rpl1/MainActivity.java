package com.example.tugasarvy11rpl1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    EditText txtuser;
    EditText txtpass;
    Button btnmasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        pref = getSharedPreferences( "login", MODE_PRIVATE );
        txtuser = findViewById( R.id.username );
        txtpass = findViewById( R.id.password );
        btnmasuk = findViewById( R.id.btngas );
        btnmasuk.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtuser.getText().toString().equalsIgnoreCase("admin"  )
                && txtpass.getText().toString().equalsIgnoreCase( "admin" )){
                    editor = pref.edit();
                    editor.putString( "username",txtuser.getText().toString() );
                    editor.putString( "status","login" );
                    editor.apply();
                    //To Activity
                    startActivity( new Intent( getApplicationContext(),RealActivity.class ) );
                    finish();
                }

            }
        } );
    }
}