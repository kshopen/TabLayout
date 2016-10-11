package com.kimsunghee.tablayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MemoActivity extends AppCompatActivity {

    Context context;
    EditText editmemo;
    LinearLayout todobtn1;
    LinearLayout infobtn1;
    LinearLayout debtbtn1;
    LinearLayout etcbtn1;
    String tableName = "todoTable";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        context = this;

        Intent intent = getIntent();

        editmemo = (EditText) findViewById(R.id.editmemo);
        todobtn1 = (LinearLayout) findViewById(R.id.todobtn1);
        infobtn1 = (LinearLayout) findViewById(R.id.infobtn1);
        debtbtn1 = (LinearLayout) findViewById(R.id.debtbtn1);
        etcbtn1 = (LinearLayout) findViewById(R.id.etcbtn1);

        todobtn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tableName = "todoTable";
            }
        });

        infobtn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tableName = "infoTable";
            }
        });

        debtbtn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tableName = "debtTable";
            }
        });

        etcbtn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tableName = "etcTable";
            }
        });

        findViewById(R.id.savememo).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String memo = editmemo.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("memo", memo);
                intent.putExtra("tableName", tableName);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
