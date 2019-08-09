package com.example.elearning.Home_Fragment.Home;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elearning.LoginAndRegister.HomeActivity;
import com.example.elearning.R;


public class DailyArticleActivity extends AppCompatActivity {

    private TextView dailyArticleTitleLV=null;
    private ImageView dailyArticlePictureIv=null;
    private TextView dailyArticleContentLV=null;
    private Button completeBtn=null;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,WindowManager.LayoutParams. FLAG_FULLSCREEN);//设置全屏显示

        setContentView(R.layout.activity_daily_article);

        dailyArticleTitleLV=(TextView)findViewById(R.id.dailyArticleTitleLV);
        dailyArticlePictureIv=(ImageView)findViewById(R.id.dailyArticlePictureIv);
        dailyArticleContentLV=(TextView)findViewById(R.id.dailyArticleContentLV);
        completeBtn=(Button) findViewById(R.id.completeBtn);

        input();

        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jumpIntent=new Intent(DailyArticleActivity.this, HomeActivity.class);
                startActivity(jumpIntent);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void input(){
        dailyArticleTitleLV.setText("这是一篇测试文章");
        dailyArticlePictureIv.setBackground(this.getResources().getDrawable(R.drawable.background_my));
        dailyArticleContentLV.setText("    Fast food is becoming more and more popular in China, especially among children and teenagers. Today, nothing is more representative of the fast pace of modern society than fast food.\n\n" +
                "    快餐变得越来越受欢迎的在中国,尤其是在儿童和青少年。今天,没有什么是更具有代表性的现代社会的快节奏比快餐。\n\n" +
                "    There are several reasons for its popularity. For one thing, it is fast and convenient. Go into a fast food restaurant, and your food will be ready in a minute. Precious time won’t be wasted in waiting-in-line to order or waiting at your table for your food to arrive. For another, its popularity is also attributed to the clean food, the excellent service and the comfortable environment of the fast food restaurants, and American style.\n\n" +
                "    快餐的流行有几个原因。首先,它是方便和快捷的。走进快餐店,和你的食物一会儿就准备好。宝贵的时间不会浪费在排队订购或等候在你的表为你的食物到达。另一方面,它的流行也归因于清洁食品、优质的服务和舒适的环境的快餐店,和美国的风格。\n\n" +
                "    However, I think that fast food isn’t healthy enough because it doesn’t compose a balanced diet and is low in nutrition. Fast food is only a good choice when you are in a hurry and we should turn to it only once in a while.\n\n" +
                "    然而,我认为快餐是不健康的,因为它不足够组成一个均衡的饮食和低营养。快餐仅仅是一个好的选择当你匆忙,我们应该向它只偶尔。");
    }
}
