package e.wodls.dailynote_calendar;

import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;

import e.wodls.dailynote_calendar.decorators.EventDecorator;
import e.wodls.dailynote_calendar.decorators.OneDayDecorator;
import e.wodls.dailynote_calendar.decorators.SaturdayDecorator;
import e.wodls.dailynote_calendar.decorators.SundayDecorator;

public class MainActivity extends AppCompatActivity {

    String time,kcal,menu;
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    Cursor cursor;
    MaterialCalendarView materialCalendarView;

 /*   materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {

        @Override
        public void onDateSelected (@NonNull MaterialCalendarView widget, @NonNull CalendarDay,
        boolean selected){
        }
    }*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendarVIew);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2018,0,1)) // 달력의 시작
                .setMaximumDate(CalendarDay.from(2030,12,28)) // 달력의 끝
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();


        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                oneDayDecorator
        );

       String[] result = {"2018,05,15","2018,06,16","2018,07,17","2018,08,18","2018,09,19"};

       new ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor());

       materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
           @Override
           public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
               int Year = date.getYear();
               int Month = date.getMonth() + 1;
               int Day = date.getDay();

               Log.i("Year test",Year + "");
               Log.i("Month test",Month + "");
               Log.i("Day test",Day + "");

               String shot_Day = Year + "," + Month + "," + Day;

               Log.i("shot_Day test",shot_Day + "");
               materialCalendarView.clearSelection();

               Toast.makeText(getApplicationContext(), shot_Day, Toast.LENGTH_SHORT).show();
           }
       });
    }

    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        String[] Time_Result;

        ApiSimulator(String[] Time_Result) { this.Time_Result = Time_Result; }

        @Override
        protected List<CalendarDay> doInBackground(Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();

            /*특정날짜 달력에 점표시해주는곳
            월은 0이 1월 년,일은 그대로
            string 문자열인 Time_Result 을 받아와서, 를 기준으로짜르고 string을 ing 로 변환 */
            for (int i =0 ; i < Time_Result.length ; i ++) {
                CalendarDay day = CalendarDay.from(calendar);
                String[] time = Time_Result[i].split(",");
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int dayy = Integer.parseInt(time[2]);

                dates.add(day);
                calendar.set(year,month-1,dayy);
            }
            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isFinishing()) {
                return;
            }

            materialCalendarView.addDecorator(new EventDecorator(Color.RED,calendarDays,MainActivity.this));
        }

    }
}
