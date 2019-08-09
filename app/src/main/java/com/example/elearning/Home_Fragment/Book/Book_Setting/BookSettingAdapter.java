package com.example.elearning.Home_Fragment.Book.Book_Setting;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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

import org.xutils.x;

import java.util.List;

import static com.example.elearning.LoginAndRegister.LoginActivity.USERNAME;

public class BookSettingAdapter extends RecyclerView.Adapter <BookSettingAdapter.MyViewHolder> {

    private List<BookSetting> bookSettings=null;

    public BookSettingAdapter(List<BookSetting> bookSettings) {
        this.bookSettings = bookSettings;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.recycler_book_item,viewGroup,false);
        final MyViewHolder myViewHolder=new MyViewHolder(view);
        myViewHolder.book1IV.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("HandlerLeak") Handler handler = new Handler() {
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case 3:
                            Toast.makeText(viewGroup.getContext(), "收藏成功!", Toast.LENGTH_LONG).show();
                            break;
                        case 30:
                            Toast.makeText(viewGroup.getContext(), "服务器错误!", Toast.LENGTH_LONG).show();
                            break;
                    }
                    super.handleMessage(msg);
                }
            };
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();


            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(viewGroup.getContext());
                builder.setTitle("提示");
                builder.setMessage("您要收藏这本书吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int position = myViewHolder.getAdapterPosition();
                        BookSetting bookSetting=bookSettings.get(position);

                        params.put("username",USERNAME);
                        params.put("coverimag",bookSetting.getBook1Id());
                        params.put("bookname",bookSetting.getBook1Name());
                        client.post("http://10.130.171.46:8080/selectbook/", params,
                                new MyTextListener(handler, 3, 30));

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
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        BookSetting activity=bookSettings.get(i);

        x.image().bind(myViewHolder.book1IV,activity.getBook1Id());
        myViewHolder.book1TV.setText(activity.getBook1Name());
    }

    @Override
    public int getItemCount() {
        return bookSettings.size();
    }


    /**
     *自定义MyViewHolder
     * */
    static class MyViewHolder extends RecyclerView.ViewHolder{
        View userActivityView;
        private ImageView book1IV;
        private TextView book1TV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userActivityView=itemView;
            book1IV=(ImageView) itemView.findViewById(R.id.book1IV);
            book1TV=(TextView)itemView.findViewById(R.id.book1TV);
        }
    }
}
