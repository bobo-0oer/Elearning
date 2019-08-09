package com.example.elearning.Home_Fragment.Home.Top_Search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elearning.R;

import java.util.List;

public class SearchAdapter extends BaseAdapter {

    private List<SearchWord> searchWordList;
    private LayoutInflater inflater;
    private TextView searchWordTV=null;
    private TextView searchWordChineseTV=null;
    private LinearLayout SearchLL=null;

    public SearchAdapter (List<SearchWord> searchWords, Context context){
        this.searchWordList=searchWords;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return searchWordList.size();
    }

    @Override
    public Object getItem(int position) {
        return searchWordList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.listview_search_item,null);//加载布局为一个视图
        SearchWord searchWords = (SearchWord) getItem(position);

        searchWordTV=(TextView)view.findViewById(R.id.searchWordTV);
        searchWordChineseTV=(TextView)view.findViewById(R.id.searchWordChineseTV);
        SearchLL=(LinearLayout) view.findViewById(R.id.searchLL);

        SearchLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "这里要对接有道词典，查询详情", Toast.LENGTH_LONG).show();
            }
        });

        searchWordTV.setText(searchWords.searchWord);//存放数据
        searchWordChineseTV.setText(searchWords.searchChinese);
        return view;
    }

}
