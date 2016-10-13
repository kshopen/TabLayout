package com.kimsunghee.tablayout;

/**
 * Created by Kimsunghee on 2016. 10. 10..
 */

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Context context;
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    private String databaseName = "memo1.db";
    private String memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        openDB();
        addMemo();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("TODO"));
        tabLayout.addTab(tabLayout.newTab().setText("INFO"));
        tabLayout.addTab(tabLayout.newTab().setText("DEBT"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);    // 너비를 모두 같게 나누어 표시

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    // 1. DB 생성 및 열기
    private void openDB() {
        try {
            databaseHelper = new DatabaseHelper(getApplicationContext(), databaseName, null, 1);    // DB생성
            database = databaseHelper.getWritableDatabase();    // getWritableDatabase() : DB를 읽거나 쓸수 있는 권한을 부여
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4. 메모 추가하기
    public void addMemo() {
        findViewById(R.id.addmemo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MemoActivity.class);
                startActivityForResult(intent, 1001);
            }
        });
    }

    // 5. MemoActivity로부터 인텐트 받아서 데이터 추가하기
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1001) {
        }
        if (resultCode == RESULT_OK) {
            memo = intent.getExtras().getString("memo");    // 메모 내용 받아서 memo에 담기
            String tableName = intent.getExtras().getString("tableName");   // 메모 타입 받아서 tableName 으로 세팅하기
            Toast.makeText(context, "테이블명: " + tableName + " / 내용: " + memo, Toast.LENGTH_SHORT).show();

            if (memo.equals("")) {
                Toast.makeText(context, "메모를 입력하지 않았습니다.", Toast.LENGTH_SHORT).show();
            } else {
                addData(tableName);
            }
        }
    }

    // 6. DB의 테이블에 데이터 추가하기
    public void addData(String tableName) {
        Toast.makeText(context, "addData 호출", Toast.LENGTH_SHORT).show();
        database.beginTransaction();
        try {
            if (database != null) {
                SQLiteStatement stmt = database.compileStatement("INSERT INTO " + tableName + "(memo) VALUES " + "(" + memo + ")");
                stmt.execute();
                database.setTransactionSuccessful();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.endTransaction();
        }
    }

}