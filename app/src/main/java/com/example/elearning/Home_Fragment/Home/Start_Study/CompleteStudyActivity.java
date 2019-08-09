package com.example.elearning.Home_Fragment.Home.Start_Study;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elearning.LoginAndRegister.HomeActivity;
import com.example.elearning.LoginAndRegister.MyTextListener;
import com.example.elearning.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.elearning.Home_Fragment.Home.Fragment_Home.COMPLETE_TIME;
import static com.example.elearning.Home_Fragment.Home.Fragment_Home.LEARNING_WORD;
import static com.example.elearning.Home_Fragment.Home.Fragment_Home.TODAYWORD;
import static com.example.elearning.LoginAndRegister.LoginActivity.USERNAME;

public class CompleteStudyActivity extends AppCompatActivity {

    private Button completeStudyBtn=null;
    private TextView todayWordTV=null;
    private TextView allTimeTV=null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_study);
        getWindow().setStatusBarColor(0x804f4f4f);

        completeStudyBtn=(Button)findViewById(R.id.completeStudyBtn);
        todayWordTV=(TextView)findViewById(R.id.todayWordTV) ;
        allTimeTV=(TextView)findViewById(R.id.allTimeTV) ;

        allTimeTV.setText(COMPLETE_TIME);

        Intent receiveIntent = getIntent();
        Bundle bundle = receiveIntent.getExtras();
        int todayWord = (int) bundle.getSerializable("complete");
        String todayWordStr= String.valueOf(todayWord);
        todayWordTV.setText(todayWordStr);

        completeStudyBtn.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("HandlerLeak")
            Handler handler = new Handler() {
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case 3:
                            break;
                        case 30:
                            Toast.makeText(CompleteStudyActivity.this, "服务器错误!", Toast.LENGTH_LONG).show();
                            break;
                    }
                    super.handleMessage(msg);
                }
            };
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();

            Date date=new Date();
            SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String CompleteStudyTime=sd.format(date);

            @Override
            public void onClick(View v) {

                params.put("username",USERNAME);
                params.put("completetime",CompleteStudyTime);
                client.post("http://10.130.171.46:8080/completerecord/", params,
                        new MyTextListener(handler, 3, 30));

                AlertDialog.Builder builder=new AlertDialog.Builder(CompleteStudyActivity.this);
                builder.setTitle("提示");
                builder.setMessage("恭喜您，打卡成功！");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @SuppressLint("HandlerLeak")
                    Handler handler = new Handler() {
                        public void handleMessage(Message msg) {
                            switch (msg.what) {
                                case 3:
                                    break;
                                case 30:
                                    Toast.makeText(CompleteStudyActivity.this, "服务器错误!", Toast.LENGTH_LONG).show();
                                    break;
                            }
                            super.handleMessage(msg);
                        }
                    };
                    AsyncHttpClient client = new AsyncHttpClient();
                    RequestParams params = new RequestParams();

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int completeword = Integer.parseInt(LEARNING_WORD)+Integer.parseInt(TODAYWORD);
                        int completetimes = Integer.parseInt(COMPLETE_TIME)+1;

                        params.put("username",USERNAME);
                        params.put("completeword",completeword);
                        params.put("completetimes",completetimes);

                        client.post("http://10.130.171.46:8080/renewstudyrecord/", params,
                                new MyTextListener(handler, 3, 30));

                        Intent jumpIntent=new Intent(CompleteStudyActivity.this, HomeActivity.class);
                        startActivity(jumpIntent);
                        finish();
                    }
                });
                builder.setCancelable(false);
                builder.create().show();
            }
        });
    }

}
