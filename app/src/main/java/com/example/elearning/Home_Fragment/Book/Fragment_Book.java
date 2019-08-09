package com.example.elearning.Home_Fragment.Book;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.elearning.Home_Fragment.Book.Book_Fragment.Fragment_allBook;
import com.example.elearning.Home_Fragment.Book.Book_Fragment.Fragment_myBook;
import com.example.elearning.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Book extends Fragment {

    private TextView allBookTv=null;
    private TextView myBookTv=null;
    private ViewPager myViewpager_1=null;
    private MyListener myListener=null;

    private FragmentTransaction fragmentTransaction;
    private List<Fragment> fragmentList_1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_book, null);

        allBookTv=(TextView) view.findViewById(R.id.allBookTV);
        myBookTv=(TextView) view.findViewById(R.id.myBookTv);
        myViewpager_1=(ViewPager) view.findViewById(R.id.myViewpager_1);
        myListener=new MyListener();

        allBookTv.setOnClickListener(myListener);
        myBookTv.setOnClickListener(myListener);

        // 获取片段管理器
        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentList_1 = new ArrayList<Fragment>();
        Fragment fragment1 = new Fragment_allBook();
        Fragment fragment2 = new Fragment_myBook();
        fragmentList_1.add(fragment1);
        fragmentList_1.add(fragment2);

        myViewpager_1.setAdapter(new Fragment_Book.MyFragmentPagerAdapter(fragmentManager,fragmentList_1));//设置适配器

        /**
         * Viewpager的事件监听
         * 滑动Viewpager时改变底部按钮
         * */
        myViewpager_1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        allBookTv.setSelected(true);
                        myBookTv.setSelected(false);
                        break;
                    case 1:
                        allBookTv.setSelected(false);
                        myBookTv.setSelected(true);
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        allBookTv.setSelected(true);
        myViewpager_1.setCurrentItem(0, true);
        return view;
    }

    /**
     * 按钮事件监听
     * */
    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case(R.id.allBookTV):
                    setSelected();
                    allBookTv.setSelected(true);
                    myViewpager_1.setCurrentItem(0, true);

                    break;
                case(R.id.myBookTv):
                    setSelected();
                    myBookTv.setSelected(true);
                    myViewpager_1.setCurrentItem(1, true);

                    break;
                    default:
                        break;
            }
        }
    }

    /**
     * 重置所有TextView未选中状态
     * */
    private void setSelected(){
        allBookTv.setSelected(false);
        myBookTv.setSelected(false);
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

}
