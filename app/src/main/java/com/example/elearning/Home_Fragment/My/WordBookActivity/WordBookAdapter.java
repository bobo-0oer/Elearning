package com.example.elearning.Home_Fragment.My.WordBookActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elearning.LoginAndRegister.MyTextListener;
import com.example.elearning.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.List;

import static com.example.elearning.LoginAndRegister.LoginActivity.USERNAME;

public class WordBookAdapter extends RecyclerView.Adapter <WordBookAdapter.MyViewHolder>  {

    private List<WordBook> wordBookList=null;
    private String WORDBOOK=null;

    public WordBookAdapter(List<WordBook> wordBooks) {
        this.wordBookList = wordBooks;
    }

    @NonNull
    @Override
    public WordBookAdapter.MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {

        final View view= LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.recycler_word_book_item,viewGroup,false);
        final MyViewHolder myViewHolder=new MyViewHolder(view);
        myViewHolder.bookIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(viewGroup.getContext());
                builder.setTitle("提示");
                builder.setMessage("确定更换单词书吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(myViewHolder.getPosition()==0){
                            WORDBOOK="wordbookcet4/";
                        }else if(myViewHolder.getPosition()==1){
                            WORDBOOK="wordbookcet6/";
                        }else if(myViewHolder.getPosition()==2){
                            WORDBOOK="wordbookky/";
                        }else if(myViewHolder.getPosition()==3){
                            WORDBOOK="wordbookgk/";
                        }else{
                            Toast.makeText(viewGroup.getContext(), "不可能执行else!", Toast.LENGTH_LONG).show();
                        }
                        Uploading();
                        Toast.makeText(viewGroup.getContext(), "更换成功!", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create().show();
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WordBookAdapter.MyViewHolder myViewHolder, int i) {
        WordBook activity=wordBookList.get(i);
        myViewHolder.bookIV.setImageResource(activity.getBookId());
        myViewHolder.bookTV.setText(activity.getBookName());
    }

    @Override
    public int getItemCount() {
        return wordBookList.size();
    }

    /**
     *自定义MyViewHolder
     * */
    static class MyViewHolder extends RecyclerView.ViewHolder{
        View userActivityView;
        private ImageView bookIV;
        private TextView bookTV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userActivityView=itemView;
            bookIV=(ImageView) itemView.findViewById(R.id.bookIV);
            bookTV=(TextView)itemView.findViewById(R.id.bookTV);
        }
    }

    /**
     * 把每个用户的单词书上传到服务器
     * */
    private void Uploading(){
        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 3:
                        break;
                    case 30:
                        break;
                }
                super.handleMessage(msg);
            }
        };
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("username", USERNAME);
        params.put("wordbooksets", WORDBOOK);
        client.post("http://10.130.171.46:8080/wordbookset/", params,
                new MyTextListener(handler, 3, 30));

    }
}
