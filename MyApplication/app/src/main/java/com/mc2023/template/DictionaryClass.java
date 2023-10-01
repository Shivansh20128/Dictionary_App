package com.mc2023.template;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DictionaryClass extends AppCompatActivity {
    private final Context context = this;
    RecyclerView recyclerView;
    List<MyModel> myModelList;
    CustomAdapter customAdapter;
    ImageButton speaker_button;
    DownloadManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_meaning);
        recyclerView = findViewById(R.id.recycler);
        Intent intent = getIntent();
        String data = intent.getStringExtra("JSONString");
        data = data.substring(1,data.length()-1);
        speaker_button = findViewById(R.id.addBtn);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myModelList = new ArrayList<>();
        customAdapter = new CustomAdapter(this, myModelList);
        recyclerView.setAdapter(customAdapter);
        try {
            JSONObject jsonObject = new JSONObject(data);
            String name = jsonObject.getString("word");
            String meanings = jsonObject.getString("meanings");
            meanings=meanings.substring(1,meanings.length()-1);
            JSONArray meaningsArray = (JSONArray) jsonObject.get("meanings");
            TextView nameView = (TextView)findViewById(R.id.the_word);

            speaker_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        String phonetics = jsonObject.getString("phonetics");
                        JSONArray phoneticsArray = new JSONArray(phonetics);
                        String link="";
                        for(int i=0;i<phoneticsArray.length();i++){
                            JSONObject phoneticsObject = phoneticsArray.getJSONObject(i);
                            if(link.equals("")){
                                link = phoneticsObject.getString("audio");
                            }else break;
                        }
                        manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                        Uri uri = Uri.parse(link);

                        DownloadManager.Request request = new DownloadManager.Request(uri);
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        request.addRequestHeader("Authorization", "Bearer <token>");
                        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
                        request.setTitle("audio_phonetics.mp3");
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"audio_phonetics.mp3");
                        request.allowScanningByMediaScanner();
                        long reference = manager.enqueue(request);
                        MediaPlayer mediaPlayer = new MediaPlayer();

                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        try {
                            mediaPlayer.setDataSource(link);
                            mediaPlayer.prepare();
                            mediaPlayer.start();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                }
            });

            ArrayList<String> stats = new ArrayList<>();
            ArrayList<String> meaning_stats = new ArrayList<>();
            for(int i = 0; i < meaningsArray.length(); i++) {
                JSONObject stat = meaningsArray.getJSONObject(i);
                stats.add(stat.getString("partOfSpeech"));
                meaning_stats.add(stat.getString("definitions"));
                myModelList.add(new MyModel(stats.get(i),meaning_stats.get(i)));
            }
            nameView.setText(name);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this,"Error : Can't parse JSON",Toast.LENGTH_SHORT).show();
        }
    }
}