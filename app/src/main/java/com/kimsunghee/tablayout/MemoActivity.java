package com.kimsunghee.tablayout;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MemoActivity extends AppCompatActivity {

    Context context;
    String tableName = "todoTable";
    private SQLiteDatabase database;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        context = this;

        SQLiteOpenHelper opener = new SQLiteOpenHelper(context, "memo1.db", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {

            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            }
        };
        database = opener.getWritableDatabase();

        findViewById(R.id.todobtn1).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tableName = "todoTable";
                toastShow();
            }
        });

        findViewById(R.id.infobtn1).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tableName = "infoTable";
                toastShow();
            }
        });

        findViewById(R.id.debtbtn1).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tableName = "debtTable";
                toastShow();
            }
        });

        findViewById(R.id.etcbtn1).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tableName = "etcTable";
                toastShow();
            }
        });

        findViewById(R.id.savememo).setOnClickListener(new View.OnClickListener(){
            private boolean flag = false;
            @Override
            public void onClick(View view) {
                String memo = ((EditText)findViewById(R.id.editmemo)).getText().toString();
                if(addData(tableName,memo)){
                    flag = true;
                    Intent intent = new Intent();
                        // (질문) insert가 성공하면 intent에 view pager 갱신 명령 flag 넣어서 MainActivity에 result로 던지기
                    intent.putExtra("result",flag);
                }
                finish();
            }
        });
    }

    // 1. DB의 테이블에 데이터 추가하기
    private boolean addData(String tableName, String memo) {
        database.beginTransaction();
        try {
            if (database != null) {
                SQLiteStatement stmt = database.compileStatement("INSERT INTO " + tableName + "(memo) VALUES " + "(" + memo + ")");
                stmt.execute();
                database.setTransactionSuccessful();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.endTransaction();
        }
        return false;
    }

    // 2. 토스트 띄우기
    private void toastShow(){
        Toast.makeText(context, tableName + " 선택", Toast.LENGTH_SHORT).show();
    }
}
