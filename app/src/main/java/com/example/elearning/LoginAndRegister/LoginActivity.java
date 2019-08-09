package com.example.elearning.LoginAndRegister;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elearning.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;


public class LoginActivity extends AppCompatActivity {

    private EditText usernameET=null;
    private EditText passwordET=null;
    private Button loginBtn=null;
    private TextView registerTV=null;
    private MyListener mylistener=null;
    public static String USERNAME=null;
    private long mPressedTime = 0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(0xffb8cab0);

        usernameET=(EditText)findViewById(R.id.usernameET);
        passwordET=(EditText)findViewById(R.id.passwordET);
        loginBtn=(Button)findViewById(R.id.loginBtn);
        registerTV=(TextView)findViewById(R.id.registerTV);
        mylistener=new MyListener();

        loginBtn.setOnClickListener(mylistener);
        registerTV.setOnClickListener(mylistener);
    }

    /**
     * 按钮事件监听
     * */
    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            @SuppressLint("HandlerLeak") Handler handler = new Handler() {
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case 3:
                            if(msg.obj.toString().equals("登陆成功!")){
                                Toast.makeText(LoginActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                                Intent jumpIntent1=new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(jumpIntent1);
                                finish();
                            } else{
                                Toast.makeText(LoginActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                            }
                            break;
                        case 30:
                            Toast.makeText(LoginActivity.this, "服务器错误，登录失败!", Toast.LENGTH_LONG).show();
                            break;
                    }
                    super.handleMessage(msg);
                }
            };
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();

            switch (v.getId()){
                case (R.id.loginBtn):
                    params.put("username", usernameET.getText().toString());
                    params.put("password", passwordET.getText().toString());
                    client.post("http://10.130.171.46:8080/login/", params,
                            new MyTextListener(handler, 3, 30));
                    USERNAME=usernameET.getText().toString();//全局变量赋值

                    break;
                case(R.id.registerTV):
                    Intent jumpIntent2=new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(jumpIntent2);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 双击退出
     * */
    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();
        if((mNowTime - mPressedTime) > 2000){
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
    }
    else{
            this.finish();
            System.exit(0);
        }
    }


}
