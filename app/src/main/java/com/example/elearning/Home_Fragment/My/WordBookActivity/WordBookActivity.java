package com.example.elearning.Home_Fragment.My.WordBookActivity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import com.example.elearning.R;

import java.util.ArrayList;
import java.util.List;

public class WordBookActivity extends AppCompatActivity {

    private List<WordBook> wordBookArrayList=new ArrayList<WordBook>();
    private RecyclerView recycler_view_WordBook=null;
    private ImageView exitWordBookIV=null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_book);
        getWindow().setStatusBarColor(0xff4f4f4f);

        recycler_view_WordBook=(RecyclerView)findViewById(R.id.recycler_view_WordBook);
        exitWordBookIV=(ImageView)findViewById(R.id.exitWordBookIV);

        wordBookArrayList.clear();
        initWordBook();

        exitWordBookIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recycler_view_WordBook.setLayoutManager(staggeredGridLayoutManager);
        WordBookAdapter wordBookAdapter=new WordBookAdapter(wordBookArrayList);//为适配器对象指定数据源
        recycler_view_WordBook.setAdapter(wordBookAdapter);
    }

    /**
     * 存入数据
     * */
    private void initWordBook(){
        WordBook wordBook1=new WordBook(R.drawable.wordbookcet4,"四级单词书");
        wordBookArrayList.add(wordBook1);
        WordBook wordBook2=new WordBook(R.drawable.wordbookcet6,"六级单词书");
        wordBookArrayList.add(wordBook2);
        WordBook wordBook3=new WordBook(R.drawable.wordbookky,"考研单词书");
        wordBookArrayList.add(wordBook3);
        WordBook wordBook4=new WordBook(R.drawable.wordbookgk,"高考单词书");
        wordBookArrayList.add(wordBook4);
    }
}
