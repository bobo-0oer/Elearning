package com.example.elearning.Home_Fragment.My.ChangePlanActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elearning.LoginAndRegister.LoginActivity;
import com.example.elearning.LoginAndRegister.MyTextListener;
import com.example.elearning.LoginAndRegister.RegisterActivity;
import com.example.elearning.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import static com.example.elearning.LoginAndRegister.LoginActivity.USERNAME;
import static com.example.elearning.R.drawable.wordbookcet4;

public class ChangePlanActivity extends AppCompatActivity {

    private ImageView exitChangePlanIV=null;
    private ImageView EnglishBookIV=null;
    private TextView DailyStudyTV=null;
    private TextView completeTV=null;
    private TextView CompleteStudyTV=null;
    private TextView EnglishBookNameTV=null;
    private RadioButton plan30=null;
    private RadioButton plan50=null;
    private RadioButton plan100=null;
    private RadioButton plan150=null;
    private RadioButton plan200=null;
    private Button queryChangeBtn=null;
    private String wordbookStr=null;

    MyListener myListener;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_plan);
        getWindow().setStatusBarColor(0xff4f4f4f);

        exitChangePlanIV=(ImageView)findViewById(R.id.exitChangePlanIV);
        EnglishBookIV=(ImageView)findViewById(R.id.EnglishBookIV);
        DailyStudyTV=(TextView)findViewById(R.id.DailyStudyTV);
        completeTV=(TextView)findViewById(R.id.completeTV);
        CompleteStudyTV=(TextView)findViewById(R.id.CompleteStudyTV);
        EnglishBookNameTV=(TextView)findViewById(R.id.EnglishBookNameTV);
        plan30=(RadioButton) findViewById(R.id.plan30);
        plan50=(RadioButton) findViewById(R.id.plan50);
        plan100=(RadioButton) findViewById(R.id.plan100);
        plan150=(RadioButton) findViewById(R.id.plan150);
        plan200=(RadioButton) findViewById(R.id.plan200);
        queryChangeBtn=(Button) findViewById(R.id.queryChangeBtn);
        myListener=new MyListener();

        InitPlan();

        exitChangePlanIV.setOnClickListener(myListener);
        plan30.setOnClickListener(myListener);
        plan50.setOnClickListener(myListener);
        plan100.setOnClickListener(myListener);
        plan150.setOnClickListener(myListener);
        plan200.setOnClickListener(myListener);
        queryChangeBtn.setOnClickListener(myListener);
    }

    /**
     * 按钮事件监听
     * */
    private class MyListener implements View.OnClickListener{

        @SuppressLint("HandlerLeak")
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 3:
                        Toast.makeText(ChangePlanActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                        break;
                    case 30:
                        Toast.makeText(ChangePlanActivity.this, "服务器错误!", Toast.LENGTH_LONG).show();
                        break;
                }
                super.handleMessage(msg);
            }
        };
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case (R.id.queryChangeBtn):
                    params.put("username",USERNAME);
                    params.put("todayword",DailyStudyTV.getText().toString());
                    client.post("http://10.130.171.46:8080/studyplan/", params,
                            new MyTextListener(handler, 3, 30));

                    break;
                case (R.id.exitChangePlanIV):
                    finish();
                    break;
                case (R.id.plan30):
                    AlertDialog.Builder builder30=new AlertDialog.Builder(ChangePlanActivity.this);
                    builder30.setTitle("提示");
                    builder30.setMessage("确定更改学习计划吗？");
                    builder30.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DailyStudyTV.setText("10");
                        }
                    });
                    builder30.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder30.create().show();
                    break;
                case (R.id.plan50):
                    AlertDialog.Builder builder50=new AlertDialog.Builder(ChangePlanActivity.this);
                    builder50.setTitle("提示");
                    builder50.setMessage("确定更改学习计划吗？");
                    builder50.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DailyStudyTV.setText("20");
                        }
                    });
                    builder50.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder50.create().show();
                    break;
                case (R.id.plan100):
                    AlertDialog.Builder builder100=new AlertDialog.Builder(ChangePlanActivity.this);
                    builder100.setTitle("提示");
                    builder100.setMessage("确定更改学习计划吗？");
                    builder100.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DailyStudyTV.setText("30");
                        }
                    });
                    builder100.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder100.create().show();
                    break;
                case (R.id.plan150):
                    AlertDialog.Builder builder150=new AlertDialog.Builder(ChangePlanActivity.this);
                    builder150.setTitle("提示");
                    builder150.setMessage("确定更改学习计划吗？");
                    builder150.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DailyStudyTV.setText("50");
                        }
                    });
                    builder150.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder150.create().show();
                    break;
                case (R.id.plan200):
                    AlertDialog.Builder builder200=new AlertDialog.Builder(ChangePlanActivity.this);
                    builder200.setTitle("提示");
                    builder200.setMessage("确定更改学习计划吗？");
                    builder200.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DailyStudyTV.setText("100");
                        }
                    });
                    builder200.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder200.create().show();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 从服务器查询单词
     * */
    private void InitPlan(){
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 3:
                        String[] strings = msg.obj.toString().split("/");
                        completeTV.setText(strings[3]);
                        DailyStudyTV.setText(strings[1]);
                        CompleteStudyTV.setText(strings[2]);
                        wordbookStr=strings[4];
                        /**
                         * 更换单词书的图片和书名
                         * */
                        if(wordbookStr.equals( "wordbookcet4")){
                            EnglishBookIV.setBackgroundDrawable(getResources().getDrawable(R.drawable.wordbookcet4));
                            EnglishBookNameTV.setText("四级单词书");
                        }else if(wordbookStr.equals( "wordbookcet6")){
                            EnglishBookIV.setBackgroundDrawable(getResources().getDrawable(R.drawable.wordbookcet6));
                            EnglishBookNameTV.setText("六级单词书");
                        }else if(wordbookStr.equals("wordbookky") ){
                            EnglishBookIV.setBackgroundDrawable(getResources().getDrawable(R.drawable.wordbookky));
                            EnglishBookNameTV.setText("考研单词书");
                        }else if(wordbookStr.equals("wordbookgk") ){
                            EnglishBookIV.setBackgroundDrawable(getResources().getDrawable(R.drawable.wordbookgk));
                            EnglishBookNameTV.setText("高考单词书");
                        }else{
                            Toast.makeText(ChangePlanActivity.this, "不可能执行else!", Toast.LENGTH_LONG).show();
                        }
                    case 30:
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
