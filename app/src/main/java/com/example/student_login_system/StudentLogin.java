package com.example.student_login_system;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import java.util.ArrayList;

public class StudentLogin extends AppCompatActivity {
    ArrayList<Student> StudentList = new ArrayList<Student>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        StudentList.add(new Student());
        Toast.makeText(getApplicationContext(), StudentList.get(0).getStudentID(), Toast.LENGTH_SHORT).show();
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
