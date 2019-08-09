package com.example.elearning.Home_Fragment.My;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.elearning.Home_Fragment.My.ChangePlanActivity.ChangePlanActivity;
import com.example.elearning.Home_Fragment.My.SetButton.HelpActivity;
import com.example.elearning.Home_Fragment.My.SetButton.InformActivity;
import com.example.elearning.Home_Fragment.My.SetButton.ProgressActivity;
import com.example.elearning.Home_Fragment.My.WordBookActivity.WordBookActivity;
import com.example.elearning.LoginAndRegister.LoginActivity;
import com.example.elearning.R;

import static com.example.elearning.LoginAndRegister.LoginActivity.USERNAME;

public class Fragment_My extends Fragment {

    private Button exitBtn=null;
    private Button changePlanBtn=null;
    private LinearLayout settingLL=null;
    private LinearLayout helpLL=null;
    private LinearLayout aboutLL=null;
    private ImageView headIV=null;
    private TextView WordBookTV=null;
    private TextView myUsernameTV=null;
    private TextView InformTV=null;
    private TextView ProgressTV=null;
    private MyListener myListener=null;
    PopupWindow popupWindow;
    String str;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my, null);

        exitBtn=(Button) view.findViewById(R.id.exitBtn);
        changePlanBtn=(Button) view.findViewById(R.id.changePlanBtn);
        settingLL=(LinearLayout) view.findViewById(R.id.settingLL);
        helpLL=(LinearLayout) view.findViewById(R.id.helpLL);
        aboutLL=(LinearLayout) view.findViewById(R.id.aboutLL);
        headIV=(ImageView) view.findViewById(R.id.headIV);
        WordBookTV=(TextView) view.findViewById(R.id.WordBookTV);
        myUsernameTV=(TextView) view.findViewById(R.id.myUsernameTV);
        InformTV=(TextView) view.findViewById(R.id.InformTV);
        ProgressTV=(TextView) view.findViewById(R.id.ProgressTV);

        myListener=new MyListener();
        myUsernameTV.setText(USERNAME);

        exitBtn.setOnClickListener(myListener);
        helpLL.setOnClickListener(myListener);
        aboutLL.setOnClickListener(myListener);
        WordBookTV.setOnClickListener(myListener);
        InformTV.setOnClickListener(myListener);
        changePlanBtn.setOnClickListener(myListener);
        ProgressTV.setOnClickListener(myListener);
        return view;
    }

    /**
     * 按钮事件监听
     * */
    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case(R.id.exitBtn):
                    Intent jumpIntent=new Intent(getActivity(), LoginActivity.class);
                    startActivity(jumpIntent);
                    getActivity().finish();
                    break;
                case(R.id.settingLL):
                    break;
                case(R.id.helpLL):
                    Intent jumpIntent2=new Intent(getActivity(), HelpActivity.class);
                    startActivity(jumpIntent2);
                    break;
                case(R.id.aboutLL):
                    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                    builder.setTitle("关于");
                    builder.setMessage("这是杨博的课设");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.create().show();
                    break;
                case(R.id.WordBookTV):
                    Intent jumpIntent3=new Intent(getActivity(), WordBookActivity.class);
                    startActivity(jumpIntent3);
                    break;
                case(R.id.InformTV):
                    Intent jumpIntent4=new Intent(getActivity(), InformActivity.class);
                    startActivity(jumpIntent4);
                    break;
                case(R.id.changePlanBtn):
                    Intent jumpIntent5=new Intent(getActivity(), ChangePlanActivity.class);
                    startActivity(jumpIntent5);
                    break;
                case(R.id.ProgressTV):
                    Intent jumpIntent6=new Intent(getActivity(), ProgressActivity.class);
                    startActivity(jumpIntent6);
                    break;

                default:
                    break;
            }
        }
    }
}
