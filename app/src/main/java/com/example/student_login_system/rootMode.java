package com.example.student_login_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class rootMode extends AppCompatActivity {

    private static final String TAG = "loginInfo";
    private static final int READ_BLOCK_SIZE = 100;
    private String fname = "note.csv";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_mode);

    }
    // Button元件的事件處理
    public void btnSave_Click(View view) {
        EditText input = (EditText) findViewById(R.id.txtInput);
        String str = input.getText().toString();
        try {
            // 開啟寫入檔案
            FileInputStream in = openFileInput(fname);
            BufferedReader sr = new BufferedReader(new InputStreamReader(in));
            String line=sr.readLine();


            FileOutputStream out = openFileOutput(fname,MODE_PRIVATE);
            OutputStreamWriter sw = new OutputStreamWriter(out);
            sw.write(str);  // 將字串寫入串流
            sw.flush();     // 輸出串流資料
            sw.close();     // 關閉串流
            Toast.makeText(this, "成功寫入檔案...",
                    Toast.LENGTH_SHORT).show();
            input.setText("");  // 清除EditText元件的內容
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void btnRead_Click(View view) {
        try {
            String str = "";
            // 開啟讀取檔案
            TextView output = (TextView) findViewById(R.id.lblOutput);
            FileInputStream in = openFileInput(fname);
            BufferedReader sr = new BufferedReader(new InputStreamReader(in));
            String line = null;
            line=sr.readLine();
            str += line+"\n";
            output.setText("讀取內容:\n" + str);
            // 讀取檔案內容
            while ((line=sr.readLine())!=null) {
                str += line+"\n";
                output.setText("讀取內容:\n" + str);
                String item[] = line.split(",");
                /** 讀取 **/
                String  data1= item[0].trim();
                String  data2= item[1].trim();
                String  data3= item[2].trim();
            }
            Toast.makeText(this, "成功讀取檔案:",
                    Toast.LENGTH_SHORT).show();
            sr.close();     // 關閉串流

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}