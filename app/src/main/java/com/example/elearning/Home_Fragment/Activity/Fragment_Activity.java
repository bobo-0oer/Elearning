package com.example.elearning.Home_Fragment.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.elearning.Home_Fragment.Activity.User_Activity.UserActivity;
import com.example.elearning.Home_Fragment.Activity.User_Activity.UserActivityAdapter;
import com.example.elearning.LoginAndRegister.JSONTOOL;
import com.example.elearning.LoginAndRegister.MyTextListener;
import com.example.elearning.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Fragment_Activity extends Fragment {

    private List<UserActivity> userActivitiesList=new ArrayList<UserActivity>();
    private RecyclerView recycler_view=null;
    private FloatingActionButton addActivityBtn=null;
    private boolean isFirstLoading = true;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_activity, null);

        addActivityBtn=(FloatingActionButton)view.findViewById(R.id.addActivityBtn);

        recycler_view=(RecyclerView)view .findViewById(R.id.recycler_view);
        userActivitiesList.clear();

        InitData();

        addActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jumpIntent=new Intent(getActivity(),AddActivity.class);
                startActivity(jumpIntent);
            }
        });
        return view;
    }


    /**
     * 加入数据的方法
     * */
    private void InitData(){
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 3:
                        List<HashMap<String,String>> map = JSONTOOL.analyze_some_json(msg.obj.toString());

                        userActivitiesList.clear();
                        for (int i=0;i<map.size();i++){
                            String url = "http://10.130.171.46:8080"+map.get(i).get("picture");
                            UserActivity userActivity = new UserActivity(R.drawable.icon_hand,map.get(i).get("username"),map.get(i).get("content"),url,R.mipmap.icon_good);
                            userActivitiesList.add(userActivity);
                        }

                        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
                        recycler_view.setLayoutManager(staggeredGridLayoutManager);
                        Collections.reverse(userActivitiesList);
                        UserActivityAdapter userActivityAdapter=new UserActivityAdapter(userActivitiesList);//为适配器对象指定数据源
                        recycler_view.setAdapter(userActivityAdapter);
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
        client.post("http://10.130.171.46:8080/getactivity/", params,
                new MyTextListener(handler, 3, 30));

    }
}
