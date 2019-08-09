package com.example.elearning.Home_Fragment.Home.Start_Study;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elearning.LoginAndRegister.HomeActivity;
import com.example.elearning.LoginAndRegister.JSONTOOL;
import com.example.elearning.LoginAndRegister.MyTextListener;
import com.example.elearning.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.elearning.Home_Fragment.Home.Fragment_Home.STUDYING_WORDBOOK;
import static com.example.elearning.Home_Fragment.Home.Fragment_Home.TODAYWORD;
import static com.example.elearning.LoginAndRegister.LoginActivity.USERNAME;


public class StartStudyActivity extends AppCompatActivity {

    private ImageView exitStudyIV=null;
    private ProgressBar learnProgressBar=null;
    private TextView englishTV=null;
    private TextView phoneticTV=null;
    private TextView knownTimeTV=null;
    private TextView lastLearnTimeTV=null;
    private TextView chineseTV=null;
    private TextView exampleTV=null;
    private Button voiceBtn=null;
    private Button knowBtn=null;
    private Button unknownBtn=null;
    private MyListener myListener=null;
    private LinearLayout translationLL=null;
    List<WordStudy> wordStudies=new ArrayList<WordStudy>();
    public static int positionList=0;//列表位置定义
    int knownBtnFlog=0;
    int unknownBtnFlog=0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_study);
        getWindow().setStatusBarColor(0xff4f4f4f);

        exitStudyIV=(ImageView)findViewById(R.id.exitStudyIV);
        learnProgressBar=(ProgressBar)findViewById(R.id.learnProgressBar);
        englishTV=(TextView) findViewById(R.id.englishTV);
        phoneticTV=(TextView) findViewById(R.id.phoneticTV);
        knownTimeTV=(TextView) findViewById(R.id.knownTimeTV);
        lastLearnTimeTV=(TextView) findViewById(R.id.lastLearnTimeTV);
        chineseTV=(TextView) findViewById(R.id.chineseTV);
        exampleTV=(TextView) findViewById(R.id.exampleTV);
        voiceBtn=(Button) findViewById(R.id.voiceBtn);
        knowBtn=(Button) findViewById(R.id.knowBtn);
        unknownBtn=(Button) findViewById(R.id.unknownBtn);
        translationLL=(LinearLayout)findViewById(R.id.translationLL) ;

        InitWordStudy();

        myListener=new MyListener();
        exitStudyIV.setOnClickListener(myListener);
        knowBtn.setOnClickListener(myListener);
        unknownBtn.setOnClickListener(myListener);
    }

    /**
     * 按钮监听
     * */
    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case (R.id.exitStudyIV):
                    Intent jumpIntent=new Intent(StartStudyActivity.this, HomeActivity.class);
                    startActivity(jumpIntent);
                    break;
                case(R.id.knowBtn):
                    knowBtnClick();
                    break;
                case(R.id.unknownBtn):
                    unknownBtnClick();
                    break;
                default:
                    break;
            }
        }
    }


    /**
     * “认识”：出汉语意思，变为“下一个”
     * “下一个”：隐藏汉语意思，变为“认识”
     * “不认识”：出汉语意思，变为“加入生词本”
     * “加入生词本”：隐藏汉语意思，出现下一个单词“变为不认识”
     * */

    /**
     * knowBtn的两种点击事件
     * 1.点击“认识按钮”：“认识”按钮变为“下一个”，出现汉语答案
     * 2.如果此时发现单词不认识，点击“不认识”，跳转不认识逻辑
     * 3.点击“下一个”：隐藏汉语意思，开始下一个
     * */
    private void knowBtnClick(){
        if (knownBtnFlog==0) {   //这个if是点“认识”的逻辑
            knowBtn.setText("下一个");
            translationLL.setVisibility(View.VISIBLE);
            knownBtnFlog=1;
        } else {                 //这个else是点“下一个”的逻辑
            knowBtn.setText("认识");
            unknownBtn.setText("不认识");
            translationLL.setVisibility(View.INVISIBLE);
            InsertInto();
            knownBtnFlog=0;
            unknownBtnFlog=0;
        }

    }
    /**
     * unknownBtn的两种点击事件
     * 1.点击“不认识”：出现汉语意思和翻译，“认识”按钮变为“下一个”，“不认识”按钮变为“加入生词本”
     * 2.点击“下一个”：将 knownBtnFlog=1 使得点击“下一个”按钮时，可以变为下一个单词
     * 3.点击“加入生词本”：弹出“加入成功”Toast，使汉语框隐藏，并且直接开始下一个单词，
     *        “下一个”按钮变为“认识”，“加入生词本”按钮变为“不认识”
     * */
    private void unknownBtnClick(){
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 3:
                        Toast.makeText(StartStudyActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                        break;
                    case 30:
                        Toast.makeText(StartStudyActivity.this, "服务器错误!", Toast.LENGTH_LONG).show();
                        break;
                }
                super.handleMessage(msg);
            }
        };
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        String word=wordStudies.get(positionList-1).getWord();
        String chinese=wordStudies.get(positionList-1).getChinese();

        if (unknownBtnFlog==0) {    //这个if是点“不认识”的逻辑
            translationLL.setVisibility(View.VISIBLE);
            knowBtn.setText("下一个");
            knownBtnFlog=1;
            unknownBtn.setText("加入生词本");
            unknownBtnFlog=1;
        } else {                    //else是“加入生词本”的逻辑

            params.put("username",USERNAME);
            params.put("word",word);
            params.put("chinese",chinese);
            client.post("http://10.130.171.46:8080/unknownword/", params,
                    new MyTextListener(handler, 3, 30));

            knowBtn.setText("认识");
            unknownBtn.setText("不认识");
            translationLL.setVisibility(View.INVISIBLE);
            InsertInto();
            knownBtnFlog=0;
            unknownBtnFlog=0;
        }

    }

    /**
     * 把列表中的数据存入各个组件
     * 并且改变进度条进度
     * */
    private void InsertInto(){


        if(positionList<wordStudies.size()){
            int progress=((positionList+1)*100)/(wordStudies.size());
            learnProgressBar.setProgress( progress);

            englishTV.setText(wordStudies.get(positionList).word);
            phoneticTV.setText(wordStudies.get(positionList).phonetic);
            knownTimeTV.setText(wordStudies.get(positionList).knownTime);
            lastLearnTimeTV.setText(wordStudies.get(positionList).lastLearnTime);
            chineseTV.setText(wordStudies.get(positionList).chinese);
            exampleTV.setText(wordStudies.get(positionList).exampleSentence);
        }else{
            Intent jumpIntent1=new Intent(StartStudyActivity.this, CompleteStudyActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("complete",wordStudies.size());
            jumpIntent1.putExtras(bundle);
            startActivity(jumpIntent1);
            finish();
        }
        positionList++;
    }


    /**
     * 定义输入方法
     */
    private void InitWordStudy(){
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 3:
                        List<HashMap<String,String>> map = JSONTOOL.analyze_some_json(msg.obj.toString());

                        for (int i=0;i<map.size();i++){
                            WordStudy wordStudy = new WordStudy(map.get(i).get("word"),map.get(i).get("voice"),map.get(i).get("pronounce"),"0","0",map.get(i).get("chinese"),map.get(i).get("example"));
                            wordStudies.add(wordStudy);
                        }
                        InsertInto();
                        break;
                    case 30:
                        Toast.makeText(StartStudyActivity.this, "服务器错误!", Toast.LENGTH_LONG).show();
                        break;
                }
                super.handleMessage(msg);
            }
        };
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("dailyplan", TODAYWORD);
        client.post("http://10.130.171.46:8080/"+STUDYING_WORDBOOK+"/", params,
                new MyTextListener(handler, 3, 30));


    }
}
