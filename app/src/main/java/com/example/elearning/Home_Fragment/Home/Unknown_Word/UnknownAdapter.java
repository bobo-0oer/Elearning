package com.example.elearning.Home_Fragment.Home.Unknown_Word;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.elearning.R;

import java.util.List;

public class UnknownAdapter extends BaseAdapter {

    private List<UnknownWord> unknownWords;
    private LayoutInflater inflater;
    private TextView unknownWordTV=null;
    private TextView unknownWordChineseTV=null;

    public UnknownAdapter (List<UnknownWord> unknownWords, Context context){
        this.unknownWords=unknownWords;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return unknownWords.size();
    }

    @Override
    public Object getItem(int position) {
        return unknownWords.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.listview_unknown_item,null);//加载布局为一个视图
        UnknownWord unknownWords = (UnknownWord) getItem(position);

        unknownWordTV=(TextView)view.findViewById(R.id.unknownWordTV);
        unknownWordChineseTV=(TextView)view.findViewById(R.id.unknownWordChineseTV);

        unknownWordTV.setText(unknownWords.Word);//存放数据
        unknownWordChineseTV.setText(unknownWords.Chinese);
        return view;
    }
}
