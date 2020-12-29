package com.example.student_login_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class StudentSign extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign);
    }

    public void sign(View view) {
        Toast.makeText(getApplicationContext(),"Sign done.",Toast.LENGTH_SHORT).show();
    }
}