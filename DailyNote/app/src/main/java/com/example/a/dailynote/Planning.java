package com.example.a.dailynote;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Planning extends AppCompatActivity {




    //DBhelper
    DatabaseHelper myDb;
    EditText editone, edittwo, editthree, editfour, editfive, editsix, editseven, editeight, editnine, editten, editeleven, edittwelve, editthirteen,
            editfourteen, editfifteen, editsixteen, editseventeen, editeightteen, editnineteen, edittwenty, edittwentyone, edittwentytwo, edittwentythree,
            edittwentyfour;
    Button btnAddData;
    Button btnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);

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


        btnAddData = (Button) findViewById(R.id.btn_save);
        AddData();
        //다시보기
        btnAddData = (Button) findViewById(R.id.btn_save);
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "저장되었습니다.", 1000).show();
                Intent MyIntent = new Intent(Planning.this, MainActivity.class);
                startActivity(MyIntent);
            }
        });

    }


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
                            Toast.makeText(Planning.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Planning.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }



}




