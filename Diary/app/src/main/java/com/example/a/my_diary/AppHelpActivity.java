package com.example.a.my_diary;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class AppHelpActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textview = new TextView(this);
        textview.setTextSize(20);
        textview.setText("나만의 일기장" + "\n" + "아몰랑");
        setContentView(textview);
    }
}
