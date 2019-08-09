package com.example.elearning.Home_Fragment.Home.Top_Search;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.elearning.LoginAndRegister.JSONTOOL;
import com.example.elearning.LoginAndRegister.MyTextListener;
import com.example.elearning.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TopSearchActivity extends AppCompatActivity {

    private ImageView exitSearchWordIV=null;
    private EditText searchET1=null;
    private Button searchBtn1=null;
    private ListView searchWordLV=null;
    MyListener myListener;
    List<SearchWord> searchWords=new ArrayList<>();//创建对象集合

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_search);
        getWindow().setStatusBarColor(0xff4f4f4f);

        exitSearchWordIV=(ImageView) findViewById(R.id.exitSearchWordIV);
        searchET1=(EditText) findViewById(R.id.searchET1);
        searchBtn1=(Button) findViewById(R.id.searchBtn1);
        searchWordLV=(ListView) findViewById(R.id.searchWordLV);
        myListener=new MyListener();

        exitSearchWordIV.setOnClickListener(myListener);
        searchBtn1.setOnClickListener(myListener);
    }

    /**
     * 从服务器查询单词
     * */
    private void InitSearch(){
        searchWords.clear();
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 3:
                        if(msg.obj.toString().length()==0){
                            Toast.makeText(TopSearchActivity.this,"此单词不存在！", Toast.LENGTH_LONG).show();
                        } else{
                            List<HashMap<String,String>> map = JSONTOOL.analyze_some_json(msg.obj.toString());
                            for (int i=0;i<map.size();i++){
                                SearchWord searchWord = new SearchWord(map.get(i).get("english"),map.get(i).get("chinese"));
                                searchWords.add(searchWord);
                            }
                            InitAdapter();
                        }
                        break;
                    case 30:
                        Toast.makeText(TopSearchActivity.this, "服务器错误!", Toast.LENGTH_LONG).show();
                        break;
                }
                super.handleMessage(msg);
            }
        };
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("search", searchET1.getText().toString());
        client.post("http://10.130.171.46:8080/search/", params,
                new MyTextListener(handler, 3, 30));
    }


    /**
     * 加入适配器
     * */
    private void InitAdapter(){
        SearchAdapter searchAdapter=new SearchAdapter(searchWords,this);
        searchWordLV.setAdapter(searchAdapter);
    }

    /**
     * 事件监听
    * */
    private class MyListener implements View. OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case (R.id.exitSearchWordIV):
                    finish();
                    break;
                case (R.id.searchBtn1):
                    InitSearch();
                    break;
                default:
                    break;
            }
        }
    }

}
