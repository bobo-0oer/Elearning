package com.example.elearning.Home_Fragment.Home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elearning.Home_Fragment.Home.Start_Study.StartStudyActivity;
import com.example.elearning.Home_Fragment.Home.Study_Calendar.CalendarActivity;
import com.example.elearning.Home_Fragment.Home.Unknown_Word.UnknownWordActivity;
import com.example.elearning.LoginAndRegister.MyTextListener;
import com.example.elearning.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import static com.example.elearning.Home_Fragment.Home.Start_Study.StartStudyActivity.positionList;
import static com.example.elearning.LoginAndRegister.LoginActivity.USERNAME;

public class Fragment_Home extends Fragment {

    private Button startStudyBtn=null;
    private Button topBtn=null;
    private TextView myNotebookTV=null;
    private TextView calendarTV=null;
    private TextView completeTimes=null;
    private TextView newTV=null;
    private TextView todayTV=null;
    private TextView learnedTV=null;
    private TextView surplusTV=null;
    private MyListener myListener=null;
    public static String TODAYWORD=null;
    public static String COMPLETE_TIME=null;
    public static String LEARNING_WORD=null;
    public static String STUDYING_WORDBOOK=null;
    private boolean isFirstLoading = true;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_home, null);
        startStudyBtn=(Button)view.findViewById(R.id.startStudyBtn);
        topBtn=(Button)view.findViewById(R.id.topBtn);
        myNotebookTV=(TextView) view.findViewById(R.id.myNotebookTV);
        calendarTV=(TextView) view.findViewById(R.id.calendarTV);
        completeTimes=(TextView) view.findViewById(R.id.completeTimes);
        newTV=(TextView) view.findViewById(R.id.newTV);
        todayTV=(TextView) view.findViewById(R.id.todayTV);
        learnedTV=(TextView) view.findViewById(R.id.learnedTV);
        surplusTV=(TextView) view.findViewById(R.id.surplusTV);

        myListener=new MyListener();
        startStudyBtn.setOnClickListener(myListener);
        topBtn.setOnClickListener(myListener);
        myNotebookTV.setOnClickListener(myListener);
        calendarTV.setOnClickListener(myListener);

        InputData();//主页存入数据

        return view;
    }


    /**
     * 按钮事件监听
     * */
    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case(R.id.startStudyBtn):
                    if(positionList > Integer.parseInt(todayTV.getText().toString())){
                        positionList=0;
                    }
                    Intent jumpIntent=new Intent(getActivity(), StartStudyActivity.class);
                    startActivity(jumpIntent);
                    break;
                case(R.id.topBtn):
                    Intent jumpIntent1=new Intent(getActivity(), DailyArticleActivity.class);
                    startActivity(jumpIntent1);
                    break;
                case(R.id.myNotebookTV):
                    Intent jumpIntent2=new Intent(getActivity(), UnknownWordActivity.class);
                    startActivity(jumpIntent2);
                    break;
                case(R.id.calendarTV):
                    Intent jumpIntent3=new Intent(getActivity(), CalendarActivity.class);
                    startActivity(jumpIntent3);
                default:
                    break;
            }
        }
    }

    /**
     * 进入页面自动刷新数据
     * */
    private void InputData(){
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 3:
                        String[] strings = msg.obj.toString().split("/");
                        newTV.setText(strings[0]);
                        todayTV.setText(strings[1]);
                        learnedTV.setText(strings[2]);
                        completeTimes.setText(strings[3]);
                        TODAYWORD= strings[1];
                        LEARNING_WORD=strings[2];
                        COMPLETE_TIME=strings[3];
                        STUDYING_WORDBOOK=strings[4];
                        surplusTV.setText(strings[1]);

                        break;
                    case 30:
                        Toast.makeText(getActivity(), "服务器错误!", Toast.LENGTH_LONG).show();
                        break;
                }
                super.handleMessage(msg);
            }
        };
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("username", USERNAME);
        client.post("http://10.130.171.46:8080/studyrecord/", params,
                new MyTextListener(handler, 3, 30));
    }
}
