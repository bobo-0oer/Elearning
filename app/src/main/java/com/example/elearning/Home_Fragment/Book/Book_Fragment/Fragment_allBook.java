package com.example.elearning.Home_Fragment.Book.Book_Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.elearning.Home_Fragment.Book.Book_Setting.BookSetting;
import com.example.elearning.Home_Fragment.Book.Book_Setting.BookSettingAdapter;
import com.example.elearning.LoginAndRegister.JSONTOOL;
import com.example.elearning.LoginAndRegister.MyTextListener;
import com.example.elearning.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.fragment_all_book)
public class Fragment_allBook extends Fragment {

    private List<BookSetting> bookSettingArrayList=new ArrayList<BookSetting>();
    private RecyclerView recycler_view1=null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_all_book, null);

        recycler_view1=(RecyclerView)view .findViewById(R.id.recycler_view1);
        bookSettingArrayList.clear();

        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 3:
                        List<HashMap<String,String>> map = JSONTOOL.analyze_some_json(msg.obj.toString());
                        for (int i=0;i<map.size();i++){
                            String url = "http://10.130.171.46:8080"+map.get(i).get("coverimag");
                            BookSetting bookSetting = new BookSetting(url,map.get(i).get("bookname"));
                            bookSettingArrayList.add(bookSetting);
                        }

                        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                        recycler_view1.setLayoutManager(staggeredGridLayoutManager);
                        BookSettingAdapter bookSettingAdapter=new BookSettingAdapter(bookSettingArrayList);//为适配器对象指定数据源
                        recycler_view1.setAdapter(bookSettingAdapter);
                        break;
                    case 30:
                        Toast.makeText(getActivity(), "服务器错误!", Toast.LENGTH_LONG).show();
                        break;
                }
                super.handleMessage(msg);
            }
        };

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.post("http://10.130.171.46:8080/readingbook/", params,
                new MyTextListener(handler, 3, 30));

        return view;
    }

}