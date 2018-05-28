package com.example.uuzaz.daily_note_third;



import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentOne extends android.support.v4.app.Fragment {

    ArrayAdapter<String> mTaskAdapter;

    public FragmentOne() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.activity_fragment_one, container, false);

        //Create the fake data
        String[] fakeData = {
                "08시~09시", "09시~10시", "10시~11시", "11시~12시", "12시~13시", "13시~14시", "14시~15시",
                "15시~16시", "16시~17시", "17시~18시", "18시~19시", "19시~20시", "20시~21시", "21시~22시",
                "22시~23시", "23시~24시", "24시~01시", "01시~02시", "02시~03시", "03시~04시", "04시~05시",
                "05시~06시", "06시~07시", "07시~08시"
        };
        List<String> tasks = new ArrayList<String>(Arrays.asList(fakeData));

        //Create the ArrayAdapter by specifying context, "how" (layout),"where" (textview), and " what" (data)
        mTaskAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item,
                R.id.list_item_task_textview,
                tasks);

        //Still need to bind adapter to the ListView
        ListView listView = (ListView) rootview.findViewById(R.id.listview_tasks);
        listView.setAdapter(mTaskAdapter);

        return rootview;
    }
}

