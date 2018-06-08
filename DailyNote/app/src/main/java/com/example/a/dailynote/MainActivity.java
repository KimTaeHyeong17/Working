package com.example.a.dailynote;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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

    //DBhelper
    DatabaseHelper myDb;
    EditText editone, edittwo, editthree, editfour, editfive, editsix, editseven, editeight, editnine, editten, editeleven, edittwelve, editthirteen,
            editfourteen, editfifteen, editsixteen, editseventeen, editeightteen, editnineteen, edittwenty, edittwentyone, edittwentytwo, edittwentythree,
            edittwentyfour;
    Button btnAddData;
    Button btnviewAll;



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
        //fab
    /*    fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "일정저장?", 1000).show();
                //기본 alert dialog  show(); //alert dialog


            }
        }); */


        //db
        myDb = new DatabaseHelper(this);

        editone = (EditText) findViewById(R.id.editText_one);
        edittwo = (EditText) findViewById(R.id.editText_two);
        editthree = (EditText) findViewById(R.id.editText_three);
        editfour = (EditText) findViewById(R.id.editText_four);
        editfive = (EditText) findViewById(R.id.editText_five);
        editsix = (EditText) findViewById(R.id.editText_six);
        editseven = (EditText) findViewById(R.id.editText_seven);
        editeight = (EditText) findViewById(R.id.editText_eight);
        editnine = (EditText) findViewById(R.id.editText_nine);
        editten = (EditText) findViewById(R.id.editText_ten);
        editeleven = (EditText) findViewById(R.id.editText_eleven);
        edittwelve = (EditText) findViewById(R.id.editText_twelve);
        editthirteen = (EditText) findViewById(R.id.editText_thirteen);
        editfourteen = (EditText) findViewById(R.id.editText_fourteen);
        editfifteen = (EditText) findViewById(R.id.editText_fifteen);
        editsixteen = (EditText) findViewById(R.id.editText_sixteen);
        editseventeen = (EditText) findViewById(R.id.editText_seventeen);
        editeightteen = (EditText) findViewById(R.id.editText_eighteen);
        editnineteen = (EditText) findViewById(R.id.editText_nineteen);
        edittwenty = (EditText) findViewById(R.id.editText_twenty);
        edittwentyone = (EditText) findViewById(R.id.editText_twentyone);
        edittwentytwo = (EditText) findViewById(R.id.editText_twentytwo);
        edittwentythree = (EditText) findViewById(R.id.editText_twentythree);
        edittwentyfour = (EditText) findViewById(R.id.editText_twentyfour);




        btnAddData = (Button) findViewById(R.id.button_add);
        btnviewAll = (Button) findViewById(R.id.button_viewAll);
        AddData();
        viewAll();

    } //oncreate

//db method
public void AddData() {
    btnAddData.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted = myDb.insertData
                            (editone.getText().toString(), edittwo.getText().toString(), editthree.getText().toString(),
                                    editfour.getText().toString(), editfive.getText().toString(), editsix.getText().toString(),
                                    editseven.getText().toString(), editeight.getText().toString(), editnine.getText().toString(),
                                    editten.getText().toString(), editeleven.getText().toString(), edittwelve.getText().toString(),
                                    editthirteen.getText().toString(), editfourteen.getText().toString(), editfifteen.getText().toString(),
                                    editsixteen.getText().toString(), editseventeen.getText().toString(), editeightteen.getText().toString(),
                                    editnineteen.getText().toString(), edittwenty.getText().toString(), edittwentyone.getText().toString(),
                                    edittwentytwo.getText().toString(), edittwentythree.getText().toString(), edittwentyfour.getText().toString()
                                    );

                    if (isInserted = true)
                        Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                }
            }
    );
}

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            //show error message
                           // showMessage("Error", "Nothing Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("ID :" + res.getString(0) + "\n");
                            buffer.append("one :" + res.getString(1) + "\n");
                            buffer.append("two :" + res.getString(2) + "\n");
                            buffer.append("three :" + res.getString(3) + "\n");
                            buffer.append("four :" + res.getString(4) + "\n");
                            buffer.append("five :" + res.getString(5) + "\n");
                            buffer.append("six :" + res.getString(6) + "\n");
                            buffer.append("seven :" + res.getString(7) + "\n");
                            buffer.append("eight :" + res.getString(8) + "\n");
                            buffer.append("nine :" + res.getString(9) + "\n");
                            buffer.append("ten :" + res.getString(10) + "\n");
                            buffer.append("eleven :" + res.getString(11) + "\n");
                            buffer.append("twelve :" + res.getString(12) + "\n");
                            buffer.append("thirteen :" + res.getString(13) + "\n");
                            buffer.append("fourteen :" + res.getString(14) + "\n");
                            buffer.append("fifteen :" + res.getString(15) + "\n");
                            buffer.append("sixteen :" + res.getString(16) + "\n");
                            buffer.append("seventeen :" + res.getString(17) + "\n");
                            buffer.append("eighteen :" + res.getString(18) + "\n");
                            buffer.append("nineteen :" + res.getString(19) + "\n");
                            buffer.append("twenty :" + res.getString(20) + "\n");
                            buffer.append("twentyone :" + res.getString(21) + "\n");
                            buffer.append("twentytwo :" + res.getString(22) + "\n");
                            buffer.append("twentythree :" + res.getString(23) + "\n");
                            buffer.append("twentyfour :" + res.getString(24) + "\n\n");
                        }
                        //show all data
                      //  showMessage("Data", buffer.toString());

                    }
                }
        );
    }

 /*   public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }  */



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

