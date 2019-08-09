package com.example.elearning.Home_Fragment.Home.Study_Calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elearning.Home_Fragment.Home.Start_Study.CompleteStudyActivity;
import com.example.elearning.LoginAndRegister.HomeActivity;
import com.example.elearning.LoginAndRegister.MyTextListener;
import com.example.elearning.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import static com.example.elearning.LoginAndRegister.LoginActivity.USERNAME;

public class CalendarActivity extends AppCompatActivity {

    private ImageView exitCalendarIV=null;
    private ListView calendarLV=null;
    private MyListener myListener;
    List<CalendarItem> calendarItems=new ArrayList<>();//创建对象集合

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        getWindow().setStatusBarColor(0xff4f4f4f);

        exitCalendarIV=(ImageView)findViewById(R.id.exitCalendarIV);
        calendarLV=(ListView)findViewById(R.id.calendarLV);
        myListener=new MyListener();

        getRecord();//放入数据

        exitCalendarIV.setOnClickListener(myListener);
    }

    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case(R.id.exitCalendarIV):
                    Intent jumpIntent=new Intent(CalendarActivity.this, HomeActivity.class);
                    startActivity(jumpIntent);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 获取数据放入
     * */
    private void InitCalendarItem(){

        CalendarAdapter calendarAdapter=new CalendarAdapter(calendarItems,this);
        calendarLV.setAdapter(calendarAdapter);
    }

    /**
     * CalendarItem的适配器
     * */
    public class CalendarAdapter extends BaseAdapter{
        private List<CalendarItem> calendarItems;
        private LayoutInflater inflater;
        private TextView calendarItemTV=null;

        public CalendarAdapter (List<CalendarItem> calendarItems, Context context){
            this.calendarItems=calendarItems;
            this.inflater=LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return calendarItems.size();
        }

        @Override
        public Object getItem(int position) {
            return calendarItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = inflater.inflate(R.layout.listview_calendar_item,null);
            CalendarItem calendarItem=(CalendarItem) getItem(position);

            calendarItemTV=(TextView)view.findViewById(R.id.calendarItemTV);
            calendarItemTV.setText(calendarItem.calendarDate);
            return view;
        }
    }


    /**
     * 每一个日期的类
     * */
    public class CalendarItem{
        String calendarDate=null;
        private CalendarItem(String calendarDate){
            this.calendarDate=calendarDate;
        }
        public String getCalendarDate() {
            return calendarDate;
        }

        public void setCalendarDate(String calendarDate) {
            this.calendarDate = calendarDate;
        }
    }

    /**
     * 取出用户的打卡记录
     * */
    private void getRecord(){
        @SuppressLint("HandlerLeak")
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 3:
                        calendarItems.clear();
                        String[] strings = msg.obj.toString().split("/");
                        for (int i=strings.length;i>0;i--){
                            CalendarItem calendarItem=new CalendarItem(strings[i-1]);
                            calendarItems.add(calendarItem);
                        }
                        InitCalendarItem();
                        break;
                    case 30:
                        Toast.makeText(CalendarActivity.this, "服务器错误!", Toast.LENGTH_LONG).show();
                        break;
                }
                super.handleMessage(msg);
            }
        };
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("username",USERNAME);
        client.post("http://10.130.171.46:8080/getcompleterecord/", params,
                new MyTextListener(handler, 3, 30));
    }
}
