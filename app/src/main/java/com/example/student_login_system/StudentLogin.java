package com.example.student_login_system;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class StudentLogin extends AppCompatActivity {
    ArrayList<Student> StudentList = new ArrayList<Student>();
    private static final int READ_BLOCK_SIZE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        StudentList.add(new Student());
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);//沒有權限時
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(StudentLogin.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }

    }

    protected void onResume () {
        super.onResume();
        TextView tv4;
        tv4 = (TextView) findViewById(R.id.tv4);
        String tv4Text = "StudentID,UserName,Password,Email\n";


        for (int index = 0,
             listzise = StudentList.size();
             index < listzise;
             index++)
        {
            tv4Text += StudentList.get(index).getStudentID() + ",";
            tv4Text += StudentList.get(index).getUsername() + ",";
            tv4Text += StudentList.get(index).getPassword() + ",";
            tv4Text += StudentList.get(index).getEmail() + "\n";
        }
        tv4.setText(tv4Text);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //    public void studentLogin(View view) {
//        int index=0;
//        String username,studentID,email,password;
//        EditText StudentID =(EditText) findViewById(R.id.studentID);
//        EditText Password =(EditText) findViewById(R.id.password);
//        for(index=0;
//            index < 1000000 ||
//                    !new Student(String.valueOf(StudentID.getText()))
//                            .equals(StudentList.get(index));
//            index++) ;
//
//        if(index>=1000000)
//            Toast.makeText(getApplicationContext(), "login success", Toast.LENGTH_SHORT).show();
//        else
//            Toast.makeText(getApplicationContext(), "login fail", Toast.LENGTH_SHORT).show();
//    }
    public void studentLogin(View view) {
        String username,studentID,email,password;
        EditText StudentID =(EditText) findViewById(R.id.studentID);
        EditText Password =(EditText) findViewById(R.id.password);
        studentID = String.valueOf(StudentID.getText());
        password = String.valueOf(Password.getText());

        int index = studentListSearch(studentID,password);
        if(index>=0){
            Toast.makeText(getApplicationContext(),"login success.",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "login fail.",
                    Toast.LENGTH_SHORT).show(); }


        Bundle bundle = new Bundle();
        bundle.putString("NAME", StudentList.get(index).getUsername());
        bundle.putString("ID", StudentList.get(index).getStudentID());
        Intent intent = new Intent(this, StudentSign.class).putExtras(bundle);
        startActivity(intent);
    }
    public void studentRegister(View view) {
        String username,studentID,email,password;
        EditText StudentID =(EditText) findViewById(R.id.studentID);
        EditText Password =(EditText) findViewById(R.id.password);
        studentID = String.valueOf(StudentID.getText());
        password = String.valueOf(Password.getText());

        while(true) {
            int index = studentListSearch(studentID);
            if (index >= 0) {
                int index_pw = studentListSearch(studentID, password);
                if (index_pw >= 0) {
                    Toast.makeText(getApplicationContext(), "register fail.",
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                StudentList.remove(index);
                Toast.makeText(getApplicationContext(), "rename success.",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                StudentList.add(new Student(studentID, password));
                Toast.makeText(getApplicationContext(), "register success.",
                        Toast.LENGTH_SHORT).show();
            }
            onResume();
            break;
        }
    }
    public void save(View view) {
        final File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String Filename = "studentList.csv";
        final File file = new File(path,Filename);
        String data = "StudentID,UserName,Password,Email\n";
        for (int index = 0,
             listzise = StudentList.size();
             index < listzise;
             index++)
        {
            data += StudentList.get(index).getStudentID() + ",";
            data += StudentList.get(index).getUsername() + ",";
            data += StudentList.get(index).getPassword() + ",";
            data += StudentList.get(index).getEmail() + "\n";
        }
        try
        {
            Toast.makeText(this,Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString(),Toast.LENGTH_LONG).show();
            file.createNewFile();
            FileOutputStream Out = new FileOutputStream(file);
            OutputStreamWriter OutWriter = new OutputStreamWriter(Out);
            OutWriter.write(data);

            OutWriter.close();
            Out.flush();
            Out.close();

            Toast.makeText(this,"Save data successful",Toast.LENGTH_LONG).show();
        }
        catch (IOException e)
        {
            Log.e("Exception","File write failed:"+e.toString());
        }

    }
    public void read(View view) {

        final File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String Filename = "studentList.csv";
        final File file = new File(path,Filename);
        FileInputStream in = null;
        String str = "";
        try {
            in = new FileInputStream(file);
            InputStreamReader sr = new InputStreamReader(in);
            char[] buffer = new char[READ_BLOCK_SIZE];
            int count;
            // 讀取檔案內容
            while ((count = sr.read(buffer)) > 0) {
                String s = String.copyValueOf(buffer,0, count);
                str += s;
                buffer = new char[READ_BLOCK_SIZE];
            }
            sr.close();     // 關閉串流
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        Toast.makeText(this, "成功讀取檔案...",
                Toast.LENGTH_SHORT).show();
        TextView output = (TextView) findViewById(R.id.tv4);
        output.setText("讀取內容:\n" + str);
    }



    public int studentListSearch(String studentID,String password) {

        int flag = -1;
        for (int index = 0,
             listzise = StudentList.size();
             index < listzise;
             index++)
        {
            if (StudentList
                    .get(index)
                    .equals(studentID,password)) {
                return index;
            }
        }
        return flag;
    }
    public int studentListSearch(String studentID) {

        int flag = -1;
        for (int index = 0,
             listzise = StudentList.size();
             index < listzise;
             index++)
        {
            if (StudentList
                    .get(index)
                    .equals(studentID)) {
                return index;
            }
        }
        return flag;
    }


}