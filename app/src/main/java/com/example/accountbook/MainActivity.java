package com.example.accountbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<SQLDatabase> incomes = new ArrayList<>();
    ArrayList<SQLDatabase> outcomes = new ArrayList<>();

    ImageButton addbutton;
    Button intentbutton;
    Button chkbutton;

    String name;
    String type;
    int year;
    int month;
    int day;
    int money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addbutton = (ImageButton)findViewById(R.id.AddButton);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDialog addDialog = new AddDialog(MainActivity.this);
                addDialog.show();
            }
        });

        intentbutton = (Button)findViewById(R.id.intentButton);
        intentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    IncomeDBManager incomeDBManager = new IncomeDBManager(MainActivity.this);
                    SQLiteDatabase database1 = incomeDBManager.getReadableDatabase();
                    Cursor cursor = database1.rawQuery("SELECT * FROM Income", null);
                    while (cursor.moveToNext()) {
                        year = cursor.getInt(cursor.getColumnIndex("year"));
                        month = cursor.getInt(cursor.getColumnIndex("month"));
                        day = cursor.getInt(cursor.getColumnIndex("day"));
                        money = cursor.getInt(cursor.getColumnIndex("money"));
                        name = cursor.getString(cursor.getColumnIndex("name"));
                        System.out.println("year:" + year + "/month:" + month + "/day:" + day + "/money:" + money + "/name:" + name);
                    }
                    OutcomeDBManager outcomeDBManager = new OutcomeDBManager(MainActivity.this);
                    SQLiteDatabase database2 = outcomeDBManager.getReadableDatabase();
                    cursor = database2.rawQuery("SELECT * FROM Outcome", null);
                    while (cursor.moveToNext()) {
                        year = cursor.getInt(cursor.getColumnIndex("year"));
                        month = cursor.getInt(cursor.getColumnIndex("month"));
                        day = cursor.getInt(cursor.getColumnIndex("day"));
                        money = cursor.getInt(cursor.getColumnIndex("money"));
                        name = cursor.getString(cursor.getColumnIndex("name"));
                        type = cursor.getString(cursor.getColumnIndex("type"));
                        System.out.println("year:" + year + "/month:" + month + "/day:" + day + "/money:" + money + "/name:" + name+"/type:"+type);
                    }

                }catch (SQLException e){

                }
            }
        });

        chkbutton = (Button)findViewById(R.id.checkbtn);
        chkbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, detail.class);
                startActivity(intent);
            }
        });

    }
}