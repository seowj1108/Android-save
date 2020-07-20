package com.example.android0720;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class NewPerson extends SQLiteOpenHelper {
    //상위 클래스에 DefaultConstructor가 없어서
    //생성자를 직접 생성
    public NewPerson(Context context){
        super(context,
                "person.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table person(" + "num INTEGER primary key autoincrement," + "name TEXT,age INTEGER);");
    }
    //SQLite 또는 App의 버전이 변경된 경우 호출되는 메소드
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//기존의 데이터를 제거하고 새로 생성
        sqLiteDatabase.execSQL("drop table person");
        onCreate(sqLiteDatabase);

    }
}