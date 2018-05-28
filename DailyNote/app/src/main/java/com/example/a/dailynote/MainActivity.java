package com.example.a.dailynote;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {



    //오늘 날짜 계산후 표시
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
    TextView mTextView;


    //달력 visibility
    ImageButton btn_calendar;
    CalendarView calendar;

    //drawer visibility
    LinearLayout drawer;
    ImageButton btn_menu;
    //돌아보기 버튼
    Button btn_lookback;




    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind view 날짜 나타내기
        mTextView = (TextView) findViewById(R.id.today_date);
        mTextView.setText(getTime());



        //달력 보이기
        setupcal();
        setupmenu();

        //다시보기
        btn_lookback = (Button) findViewById(R.id.btn_lookback);
        btn_lookback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "돌아보기", 1000).show();
                Intent MyIntent = new Intent(MainActivity.this, Look_Back.class);
                startActivity(MyIntent);
            }
        });

    }

    //오늘 날짜 계산후 표시
    private String getTime() {
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }


    //달력 visibility 설정
    private void setupcal() {
        calendar = (CalendarView) findViewById(R.id.calendar);
        btn_calendar = (ImageButton) findViewById(R.id.btn_calendar);
        btn_calendar.setOnClickListener(calListner);
    }

    View.OnClickListener calListner = new View.OnClickListener() {
        @SuppressLint("WrongConstant")
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "달력버튼", 1000).show();
            calendar.setVisibility(calendar.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);

        }
    };

    //drawer visibility 설정
    private void setupmenu() {
        drawer = (LinearLayout) findViewById(R.id.drawer);
        btn_menu = (ImageButton) findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(menuListener);
    }

    View.OnClickListener menuListener = new View.OnClickListener() {
        @SuppressLint("WrongConstant")
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "메뉴버튼", 1000).show();
            drawer.setVisibility(drawer.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);

        }
    };


}

