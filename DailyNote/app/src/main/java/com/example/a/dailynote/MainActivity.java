package com.example.a.dailynote;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.nfc.Tag;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

    //floatingActionButton
    //  FloatingActionButton fab;

    //alert dialog
    TextView text;
    String str = "";


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
                getIntent().putExtra("strbox",str);
                startActivity(MyIntent);
            }
        });
        //fab
    /*    fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "일정저장?", 1000).show();
                //기본 alert dialog  show(); //alert dialog


            }
        }); */
          text = (TextView) findViewById(R.id.text);


    } //oncreate

    //onClick 속성이 부여된 View를 클릭했을 때 자동으로 호출되는 메소드

    public void mOnClick(View v) {

        switch (v.getId()) {
            case R.id.tmpbtn://멤버 추가(데이터 추가)

                //Dialog에서 보여줄 입력화면 View 객체 생성 작업
                //Layout xml 리소스 파일을 View 객체로 부불려 주는(inflate) LayoutInflater 객체 생성
                LayoutInflater inflater = getLayoutInflater();
                //res폴더>>layout폴더>>dialog_addmember.xml 레이아웃 리소스 파일로 View 객체 생성
                //Dialog의 listener에서 사용하기 위해 final로 참조변수 선언
                final View dialogView = inflater.inflate(R.layout.dialog_finishday, null);
                //멤버의 세부내역 입력 Dialog 생성 및 보이기

                AlertDialog.Builder buider = new AlertDialog.Builder(this); //AlertDialog.Builder 객체 생성
                buider.setTitle("Member Information"); //Dialog 제목
                buider.setIcon(android.R.drawable.ic_menu_add); //제목옆의 아이콘 이미지(원하는 이미지 설정)
                buider.setView(dialogView); //위에서 inflater가 만든 dialogView 객체 세팅 (Customize)
                buider.setPositiveButton("Complite", new DialogInterface.OnClickListener() {
                    //Dialog에 "Complite"라는 타이틀의 버튼을 설정
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // TODO Auto-generated method stub

                        //멤버 정보의 입력을 완료하고 TextView에 추가 하도록 하는 작업 수행

                        //dialogView 객체 안에서 NAME을 입력받는 EditText 객체 찾아오기(주의: dialaogView에서 find 해야함)

                        EditText edit_name = (EditText) dialogView.findViewById(R.id.dialog_edit);
                        //dialogView 객체 안에서 NATION을 입력받는 RadioGroup 객체 찾아오기

                        RadioGroup rg = (RadioGroup) dialogView.findViewById(R.id.dialog_rg);

                        //EditText에 입력된 이름 얻어오기 여기복붙
                        String name = edit_name.getText().toString();
                        //선택된 RadioButton의 ID를 RadioGroup에게 얻어오기
                        int checkedId = rg.getCheckedRadioButtonId();
                        //Check 된 RadioButton의 ID로 라디오버튼 객체 찾아오기
                        RadioButton rb = (RadioButton) rg.findViewById(checkedId);
                        String nation = rb.getText().toString();//RadionButton의 Text 얻어오기
                        //TextView의 이전 Text에 새로 입력된 멤버의 데이터를 추가하기
                        //TextView로 멤버리스트를 보여주는 것은 바람직하지 않음.다음 포스트에서 ListView로 처리합니다.
                        String s = name + "  |  " + nation + "\n";
                        str += s;
                        text.setText(str);
                        //TextView에 추가작업을 완료 하였기에 '완료'했다는 메세지를 Toast로 출력
                        Toast.makeText(MainActivity.this, "새로운 멤버를 추가했습니다", Toast.LENGTH_SHORT).show();
                    }
                });
                buider.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    //Dialog에 "Cancel"이라는 타이틀의 버튼을 설정
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        //멤버 정보의 입력을 취소하고 Dialog를 종료하는 작업

                        //취소하였기에 특별한 작업은 없고 '취소'했다는 메세지만 Toast로 출력

                        Toast.makeText(MainActivity.this, "멤버 추가를 취소합니다", Toast.LENGTH_SHORT).show();

                    }

                });

                //설정한 값으로 AlertDialog 객체 생성
                AlertDialog dialog = buider.create();
                //Dialog의 바깥쪽을 터치했을 때 Dialog를 없앨지 설정
                dialog.setCanceledOnTouchOutside(false);//없어지지 않도록 설정
                //Dialog 보이기
                dialog.show();
                break;
        }//switch

    }//mOnClickMethod


    //alert dialog show
/*
    void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final EditText edittext = new EditText(MainActivity.this);
        builder.setView(edittext);

        builder.setTitle("오늘 하루를 끝내시겠습니까?");
        builder.setMessage("진짜로?");
        setContentView(edittext);

        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "예를 선택했습니다.", Toast.LENGTH_LONG).show();

                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "아니오를 선택했습니다.", Toast.LENGTH_LONG).show();
                    }
                });
       builder.show();

    }
*/

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

