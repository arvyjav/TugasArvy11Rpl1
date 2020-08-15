package com.example.tugasarvy11rpl1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class ControllClass extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        sharedPreferences = getSharedPreferences( "login", Context.MODE_PRIVATE );
        if (sharedPreferences.getString( "username" ,"").isEmpty() ||
        sharedPreferences.getString( "username" ,"").equalsIgnoreCase( "" )){
            startActivity( new Intent( getApplicationContext(),MainActivity.class ) );
            finish();
        }else{
            startActivity( new Intent( getApplicationContext(),RealActivity.class ) );
            finish();
        }
    }
}