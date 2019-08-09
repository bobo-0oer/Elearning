package com.example.elearning.LoginAndRegister;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elearning.Home_Fragment.Activity.Fragment_Activity;
import com.example.elearning.Home_Fragment.Book.Fragment_Book;
import com.example.elearning.Home_Fragment.Home.Fragment_Home;
import com.example.elearning.Home_Fragment.Home.Top_Search.TopSearchActivity;
import com.example.elearning.Home_Fragment.My.Fragment_My;
import com.example.elearning.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends FragmentActivity {


    private TextView searchET=null;
    private Button searchBtn=null;
    private TextView homeTV=null;
    private TextView bookTV=null;
    private TextView activityTV=null;
    private TextView myTV=null;
    private ViewPager myViewpager=null;
    private MyListener myListener=null;
    private long mPressedTime = 0;

    private FragmentTransaction fragmentTransaction;
    private List<Fragment> fragmentList;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setStatusBarColor(0xff009999);

        searchET=(TextView)findViewById(R.id.searchET);
        searchBtn=(Button)findViewById(R.id.searchBtn);
        homeTV=(TextView) findViewById(R.id.homeTV);
        bookTV=(TextView) findViewById(R.id.bookTV);
        activityTV=(TextView) findViewById(R.id.activityTV);
        myTV=(TextView) findViewById(R.id.myTV);
        myViewpager=(ViewPager)findViewById(R.id.myViewpager);

        myListener=new MyListener();
        homeTV.setOnClickListener(myListener);
        bookTV.setOnClickListener(myListener);
        activityTV.setOnClickListener(myListener);
        myTV.setOnClickListener(myListener);
        searchET.setOnClickListener(myListener);
        searchBtn.setOnClickListener(myListener);

        // 获取片段管理器
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentList = new ArrayList<Fragment>();
        Fragment fragment1 = new Fragment_Home();
        Fragment fragment2 = new Fragment_Book();
        Fragment fragment3 = new Fragment_Activity();
        Fragment fragment4 = new Fragment_My();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);

        myViewpager.setAdapter(new MyFragmentPagerAdapter(fragmentManager,fragmentList));//设置适配器

        homeTV.setSelected(true);
        myViewpager.setCurrentItem(0, true);


        /**
         * Viewpager的事件监听
         * 滑动Viewpager时改变底部按钮
         * */
        myViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        homeTV.setSelected(true);
                        bookTV.setSelected(false);
                        activityTV.setSelected(false);
                        myTV.setSelected(false);
                        break;
                    case 1:
                        homeTV.setSelected(false);
                        bookTV.setSelected(true);
                        activityTV.setSelected(false);
                        myTV.setSelected(false);
                        break;
                    case 2:
                        homeTV.setSelected(false);
                        bookTV.setSelected(false);
                        activityTV.setSelected(true);
                        myTV.setSelected(false);
                        break;
                    case 3:
                        homeTV.setSelected(false);
                        bookTV.setSelected(false);
                        activityTV.setSelected(false);
                        myTV.setSelected(true);
                        break;
                        default:
                            break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });


    }

    /**
     * 按钮事件监听
     * */
    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case (R.id.homeTV):
                    setSelected();
                    homeTV.setSelected(true);
                    myViewpager.setCurrentItem(0, true);
                    break;
                case(R.id.bookTV):
                    setSelected();
                    bookTV.setSelected(true);
                    myViewpager.setCurrentItem(1, true);
                    break;
                case (R.id.activityTV):
                    setSelected();
                    activityTV.setSelected(true);
                    myViewpager.setCurrentItem(2, true);
                    break;
                case(R.id.myTV):
                    setSelected();
                    myTV.setSelected(true);
                    myViewpager.setCurrentItem(3, true);
                    break;
                case(R.id.searchET):
                    Intent jumpIntent1=new Intent(HomeActivity.this, TopSearchActivity.class);
                    startActivity(jumpIntent1);
                    break;
                case(R.id.searchBtn):
                    Intent jumpIntent2=new Intent(HomeActivity.this, TopSearchActivity.class);
                    startActivity(jumpIntent2);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 设置适配器
     * */
    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> list;
        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }
        @Override
        public int getCount() {
            return list.size();
        }
    }

    /**
     * 重置所有TextView未选中状态
     * */
    private void setSelected(){
        homeTV.setSelected(false);
        bookTV.setSelected(false);
        activityTV.setSelected(false);
        myTV.setSelected(false);
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
