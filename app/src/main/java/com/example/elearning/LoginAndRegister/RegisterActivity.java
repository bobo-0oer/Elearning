package com.example.elearning.LoginAndRegister;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.elearning.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameET_1=null;
    private EditText passwordET_1=null;
    private EditText queryPasswordET=null;
    private EditText phoneET=null;
    private EditText emailET=null;
    private Button registerBtn_1=null;

    private MyListener myListener=null;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setStatusBarColor(0xffb8cab0);

        usernameET_1=(EditText)findViewById(R.id.usernameET_1);
        passwordET_1=(EditText)findViewById(R.id.passwordET_1);
        queryPasswordET=(EditText)findViewById(R.id.queryPasswordET);
        phoneET=(EditText)findViewById(R.id.phoneET);
        emailET=(EditText)findViewById(R.id.emailET);
        registerBtn_1=(Button)findViewById(R.id.registerBtn_1);
        myListener=new MyListener();

        registerBtn_1.setOnClickListener(myListener);
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
                            if(msg.obj.toString().equals("用户名重复!")){
                                Toast.makeText(RegisterActivity.this,msg.obj.toString() , Toast.LENGTH_LONG).show();
                            } else{
                                AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                                builder.setTitle("提示");
                                builder.setMessage("恭喜您，注册成功！");
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent jumpIntent=new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(jumpIntent);
                                        finish();
                                    }
                                });
                                builder.create().show();
                                Toast.makeText(RegisterActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                            }
                            break;
                        case 30:
                            Toast.makeText(RegisterActivity .this, "服务器错误，注册失败!", Toast.LENGTH_LONG).show();
                            break;
                    }
                    super.handleMessage(msg);
                }
            };
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();

            String result=checkInfo();
            switch (v.getId()){
                case (R.id.registerBtn_1):
                    if(result==null){
                        params.put("username", usernameET_1.getText().toString());
                        params.put("password", passwordET_1.getText().toString());
                        params.put("phone", phoneET.getText().toString());
                        params.put("email", emailET.getText().toString());
                        client.post("http://10.130.171.46:8080/register/", params,
                                new MyTextListener(handler, 3, 30));
                    }else{
                        AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                        builder.setTitle("错误提示！");
                        builder.setMessage(result);
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        builder.create().show();
                    }
                    break;
                default:
                    break;
            }
        }
    }



    /**
     * 输入内容检测
     * */
    String checkInfo(){
        if (usernameET_1.getText().toString().trim()==null || usernameET_1.getText().toString().trim().equals("")){
            return "用户名不能为空!";
        }
        if(passwordET_1.getText().toString().trim().length()<6 ||passwordET_1.getText().toString().trim().length()>15 ){
            passwordET_1.setText("");
            queryPasswordET.setText("");
            return "密码长度有误！请重新输入6-15位之间！";
        }
        if(!queryPasswordET.getText().toString().trim().equals(passwordET_1.getText().toString().trim())){
            passwordET_1.setText("");
            queryPasswordET.setText("");
            return "两次输入的密码不一致！请重新输入！";
        }
        if(phoneET.getText().toString().trim().length()<11 ||phoneET.getText().toString().trim().length()>11 ){
            phoneET.setText("");
            return "联系方式输入有误！请重新输入11位联系方式！";
        }
        if(isEmail(emailET.getText().toString().trim())==false){
            emailET.setText("");
            return "邮箱格式有误！请重新输入！";
        }
        return null;
    }


    /**
     * 判断是否是邮箱.
     * @param str 指定的字符串
     * @return 是否是邮箱:是为true，否则false
     */
    public static Boolean isEmail(String str) {
        Boolean isEmail = false;
        String expr = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if (str.matches(expr)) {
            isEmail = true;
        }
        return isEmail;
    }
}
