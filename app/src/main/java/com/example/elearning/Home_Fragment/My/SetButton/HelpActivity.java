package com.example.elearning.Home_Fragment.My.SetButton;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elearning.R;

public class HelpActivity extends AppCompatActivity {

    private ImageView exitHelpIV=null;
    private TextView HelpTV=null;
    MyListener myListener;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getWindow().setStatusBarColor(0xff4f4f4f);

        HelpTV=(TextView)findViewById(R.id.HelpTV);
        exitHelpIV=(ImageView)findViewById(R.id.exitHelpIV);

        HelpTV.setText("功能描述：\n" +
                "本系统旨在为高中到大学不同学习阶段的用户提供英语单词学习、英文书籍阅读、学习计划制定、学习经验交流等功能。\n" +
                "\n" +
                "英语学习的重中之重是词汇量的积累和阅读能力的培养，为此本系统的主要功能集中体现在这两个方面，辅以学习经验的交流可以使用户不再只是个人学习，可以看到其他人的学习动态与自己的学习状况对比，或者学习其他用户的学习方法。在功能方面主要集中在以下五个方面：\n" +
                "1：单词查询模块：每个页面上都有一个单词查询入口，可以实现不认识单词的英汉或汉英方式的查询。词典的数据库中存入了十余万个常见单词的英文和翻译，通过模糊查询的方法可以得到待查询内容的所有相近和同义单词的英汉双语显示。同时数据库中也存在大量的短语，在查询过程中也会显示出来供用户理解和参考。\n" +
                "2：学习计划模块：每个用户有各自的学习计划和学习情况，这些数据会上传服务器进行保存，通过“我的”界面可以设置学习的单词书和每天的学习计划，在主页面会显示每个用户的学习情况，包含打卡次数、学习总单词数、每日学习计划，用户可以直观地看到自己的详细情况。\n" +
                "3：单词记忆模块：主界面为单词记忆模块，系统会自动从用户所选的单词书内随机取出计划数的单词，用户开始学习后需要根据自身情况判断单词是否认识和是否需要加入生词本，完成今日的学习计划后会跳出打卡界面，用户点击打卡，系统将会获取打卡时间传到服务器，并会在打卡记录界面显示，点击打卡同时会更新计划数量的单词和打卡次数、学习单词数。\n" +
                "4：书籍阅读模块：系统管理员通过管理界面添加书籍的封面、书名、内容，客户端展示出每一本书供用户阅读，用户可以选择自己喜欢的书进行收藏。每个用户登录后可以看到自己收藏过的书籍。\n" +
                "5：用户交互模块：每个用户通过用户交互模块可以发送自己想要发送的内容，可以包括文字或图片，并且可以看到其他用户的动态，点赞功能可以点赞自己喜欢的动态。主要做分享和交流作用。");

        myListener=new MyListener();
        exitHelpIV.setOnClickListener(myListener);
    }

    /**
     * 按钮监听
     * */
    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case(R.id.exitHelpIV):
                    finish();
                    break;
                default:
                    break;
            }
        }
    }
}
