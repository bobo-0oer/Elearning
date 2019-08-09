package com.example.elearning.Home_Fragment.Home.Unknown_Word;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.elearning.LoginAndRegister.HomeActivity;
import com.example.elearning.LoginAndRegister.MyTextListener;
import com.example.elearning.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import static com.example.elearning.LoginAndRegister.LoginActivity.USERNAME;

public class UnknownWordActivity extends AppCompatActivity {

    private ImageView exitUnknownWordIV=null;
    private ListView UnknownWordLV=null;
    List<UnknownWord> unknownWords=new ArrayList<>();//创建对象集合

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unknown_word);
        getWindow().setStatusBarColor(0xff4f4f4f);

        exitUnknownWordIV=(ImageView)findViewById(R.id.exitUnknownWordIV);
        UnknownWordLV=(ListView)findViewById(R.id.UnknownWordLV);
        InitUnknownAdapter();//存入单词方法

        exitUnknownWordIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jumpIntent=new Intent(UnknownWordActivity.this, HomeActivity.class);
                startActivity(jumpIntent);
            }
        });
    }



    /**
     * 获得每个用户的数据存入生词表
     * */
    private void InitUnknownAdapter(){
        @SuppressLint("HandlerLeak")
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 3:
                        unknownWords.clear();
                        String[] strings = msg.obj.toString().split("/");
                        List word = new ArrayList();
                        List chinese = new ArrayList();
                        for (int i=0;i<strings.length;i++){
                            if(i<(strings.length/2)){
                                word.add(strings[i]);
                            }else{
                                chinese.add(strings[i]);
                            }
                        }
                        for(int j=word.size()-1;j>=0;j--) {
                            UnknownWord unknownWord=new UnknownWord(String.valueOf(word.get(j)),String.valueOf(chinese.get(j)));
                            unknownWords.add(unknownWord);
                        }

                        InitUnknownItem();
                        break;
                    case 30:
                        Toast.makeText(UnknownWordActivity.this, "服务器错误!", Toast.LENGTH_LONG).show();
                        break;
                }
                super.handleMessage(msg);
            }
        };
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("username",USERNAME);
        client.post("http://10.130.171.46:8080/getunknownword/", params,
                new MyTextListener(handler, 3, 30));
    }

    /**
     * 获取数据放入
     **/
    private void InitUnknownItem(){
        UnknownAdapter unknownAdapter=new UnknownAdapter(unknownWords,this);
        UnknownWordLV.setAdapter(unknownAdapter);
    }

}
