package com.example.elearning.Home_Fragment.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.elearning.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

import static com.example.elearning.LoginAndRegister.LoginActivity.USERNAME;

public class AddActivity extends AppCompatActivity {

    private static final int IMAGE = 1;
    private ImageView exitActivityIV=null;
    private ImageView addActivityIV=null;
    private EditText addActivityET=null;
    private Button selectPhotoBtn=null;
    private Button AddActivityBtn=null;
    private String path=null;
    MyListener myListener;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getWindow().setStatusBarColor(0xff009999);

        exitActivityIV=(ImageView)findViewById(R.id.exitActivityIV);
        addActivityIV=(ImageView)findViewById(R.id.addActivityIV);
        addActivityET=(EditText) findViewById(R.id.addActivityET);
        selectPhotoBtn=(Button) findViewById(R.id.selectPhotoBtn);
        AddActivityBtn=(Button) findViewById(R.id.AddActivityBtn);
        myListener=new MyListener();



        exitActivityIV.setOnClickListener(myListener);
        selectPhotoBtn.setOnClickListener(myListener);
        AddActivityBtn.setOnClickListener(myListener);
    }

    /**
     * 按钮事件监听
     * */
    private class MyListener implements View.OnClickListener{

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


        @Override
        public void onClick(final View v) {
            switch (v.getId()){
                case (R.id.exitActivityIV):
                    finish();
                    break;
                case(R.id.selectPhotoBtn):
                    if(ContextCompat.checkSelfPermission(AddActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(AddActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);//运行时申请权限
                    }else {
                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, IMAGE);
                    }
                    break;
                case (R.id.AddActivityBtn):
                    Toast.makeText(v.getContext(), "正在发布，请稍候!", Toast.LENGTH_LONG).show();
                    RequestParams params = new RequestParams("http://10.130.171.46:8080/uploadingactivity/");

                    params.addQueryStringParameter("username",USERNAME);
                    params.addQueryStringParameter("icon","icon");
                    params.addQueryStringParameter("content",addActivityET.getText().toString());
                    params.setMultipart(true);
                    params.addBodyParameter("picture",new File(path),"multipart/form-data");

                    x.http().post(params, new Callback.CommonCallback<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    Toast.makeText(v.getContext(), "发布成功!", Toast.LENGTH_SHORT).show();
                                }
                                @Override
                                public void onError(Throwable ex, boolean isOnCallback) {
                                }
                                @Override
                                public void onCancelled(CancelledException cex) {
                                }
                                @Override
                                public void onFinished() {
                                }
                            });
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String [] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
    }
    /**
     * 加载图片
     * */
    private void showImage(String imaePath){
        Bitmap bm = BitmapFactory.decodeFile(imaePath);
        addActivityIV.setImageBitmap(bm);
    }

}

