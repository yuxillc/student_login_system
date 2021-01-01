package com.example.student_login_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentSign extends AppCompatActivity {
    private static final String TAG ="image";
    static final int REQUEST_IMAGE_CAPTURE = 100;
    private static Context context;
    String ID="Uffffff";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign);

        Bundle bundle = this.getIntent().getExtras();
        if(bundle!=null) {
            Toast.makeText(getApplicationContext(), bundle.getString("NAME")+" welcome",
                    Toast.LENGTH_LONG).show();
            ID=bundle.getString("ID");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView tv4 = (TextView) findViewById(R.id.textView4);
        tv4.setText(ID+",welcome");
    }

    public void sign(View view) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        Date date=new Date();
//        Log.i(TAG,ID+sdf.format(date)+".img");
//        File img=new File(context.getFilesDir(), ID+sdf.format(date)+".img");
//        try{
//            if(img.createNewFile())
//                System.out.println("文件创建成功！");
//            else
//                System.out.println("出错了，该文件已经存在。");
//        }
//        catch(IOException ioe) {
//            ioe.printStackTrace();
//        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        Toast.makeText(getApplicationContext(),"Sign done.",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap thumbnail = data.getParcelableExtra("data");
            // Do other work with full size photo saved in locationForPhotos
            ImageView iv3= (ImageView) findViewById(R.id.imageView3);
            iv3.setImageBitmap(thumbnail);
        }
    }
}