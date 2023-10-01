package com.mc2023.template;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Fragment_Meaning extends Fragment {
    TextView meaning;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__page, container, false);
        meaning = view.findViewById(R.id.content_id);

        String strtext = getArguments().getString("edttext");
        strtext = strtext.substring(2,strtext.length()-2);
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        String[] temp;
        temp = strtext.split("[}],[{]");
        String meri_string = "";
        for(int i =0;i<temp.length;i++){
            ArrayList<String> arr = new ArrayList<>();
            arr.add(temp[i]);
            meri_string = meri_string + temp[i] + "\n\n";
            data.add(arr);
        }
        meaning.setText(meri_string);
        return view;
    }
}