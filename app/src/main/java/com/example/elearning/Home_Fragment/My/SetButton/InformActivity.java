package com.example.elearning.Home_Fragment.My.SetButton;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.elearning.R;

import java.util.ArrayList;
import java.util.List;

public class InformActivity extends AppCompatActivity {

    private ImageView exitInformIV=null;
    private ListView InformLV=null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);
        getWindow().setStatusBarColor(0xff4f4f4f);

        exitInformIV=(ImageView)findViewById(R.id.exitInformIV);
        InformLV=(ListView) findViewById(R.id.InformLV);

        InitInformItem();

        exitInformIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 获取数据放入
     * */
    private void InitInformItem(){
        List<InformItem> informItems=new ArrayList<>();//创建对象集合
        informItems.clear();

        InformItem informItem=new InformItem("这是一条通知");
        informItems.add(informItem);
        InformItem informItem1=new InformItem("这是另一条通知");
        informItems.add(informItem1);
        InformItem informItem2=new InformItem("这是另二条通知");
        informItems.add(informItem2);

        InformAdapter informAdapter=new InformAdapter(informItems,this);
        InformLV.setAdapter(informAdapter);
    }

    /**
     * CalendarItem的适配器
     * */
    public class InformAdapter extends BaseAdapter {
        private List<InformItem> informItems;
        private LayoutInflater inflater;
        private TextView InformItemTV=null;

        public InformAdapter (List<InformItem> informItems, Context context){
            this.informItems=informItems;
            this.inflater=LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return informItems.size();
        }

        @Override
        public Object getItem(int position) {
            return informItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = inflater.inflate(R.layout.listview_inform_item,null);
            InformItem informItem=(InformItem) getItem(position);

            InformItemTV=(TextView)view.findViewById(R.id.InformItemTV);
            InformItemTV.setText(informItem.Informs);
            return view;
        }
    }


    /**
     * 每一个通知的类
     * */
    public class InformItem{
        String Informs=null;

        public String getInforms() {
            return Informs;
        }

        public void setInforms(String informs) {
            Informs = informs;
        }

        private InformItem(String Informs){
            this.Informs=Informs;
        }
    }
}
