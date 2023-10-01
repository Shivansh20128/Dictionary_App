package com.mc2023.template;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements OnDataSendToActivity{
    Button find_button;
    EditText search_word;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        find_button = findViewById(R.id.find_meaning);

        find_button.setOnClickListener(v -> {
            try {
                fetchMon();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    void fetchMon() throws IOException {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if(netinfo == null || !netinfo.isConnected()) {
            Toast.makeText(this, "Not connected to Network", Toast.LENGTH_SHORT).show();
        }
        else
        {
            search_word = findViewById(R.id.word);
            URL url =  new URL("https://api.dictionaryapi.dev/api/v2/entries/en/"+search_word.getText().toString().toLowerCase());
            AsyncTask<URL, Void, String> task = new Query_class(this).execute(url);
        }
    }

    public void updateUI(String data){
        Intent i = new Intent(this, DictionaryClass.class);
        if(data.equals("FAILED"))
        {
            Toast.makeText(this,"Word not found. Try entering world", Toast.LENGTH_LONG).show();
        }
        else {
            i.putExtra("JSONString", data);
            startActivity(i);
            Log.d("JSON", data);
        }

    }
}
