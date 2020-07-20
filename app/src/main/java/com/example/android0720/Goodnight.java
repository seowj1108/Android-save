package com.example.android0720;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Goodnight extends AppCompatActivity {
    //뷰에 대한 참조
    private Button
            btninput,btnread,btndelect,btnupdate;
    private TextView txtdisplay;
    //데이터베이스 사용을 위한 변수
    private NewPerson dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodnight);

        //뷰에 대한 참조 생성
        btninput = (Button)findViewById(R.id.btninput);
        btnread = (Button)findViewById(R.id.btnread);
        btnupdate = (Button)findViewById(R.id.btnupdate);
        btndelect = (Button)findViewById(R.id.btndelete);
        txtdisplay = (TextView)findViewById(R.id.txtdisplay);

        //데이터베이스 사용을 위한 참조도 생성
        dbHelper = new NewPerson(Goodnight.this);

        //버튼 4개의 클릭 이벤트를 처리할 객체를 생성
        Button.OnClickListener buttonHandler = new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                //변수를 선언하고 시작
                //데이터베이스 사용을 위한 변수
                SQLiteDatabase db;
                //데이터를 저장하기 위한 변수
                ContentValues row;
                switch (view.getId()){
                    case R.id.btninput:
                    //데이터 삽입
                    //데이터베이스 사용 객체를 생성
                    db = dbHelper.getWritableDatabase();
                    /*db.execSQL("insert into person(name, age)" + "values('효정',27);");

                     */
                    //ORM 형태로 데이터 삽입
                        row = new ContentValues();
                        row.put("name","아린");
                        row.put("age",22);
                        //데이터 삽입
                        db.insert("person",null,row);
                    //데이터베이스 닫기
                        dbHelper.close();
                        break;
                    case R.id.btnread:
                        //데이터베이스 사용 객체를 생성
                        db = dbHelper.getReadableDatabase();
                        //데이터베이스에서 읽기 작업 수행
                        Cursor cursor = db.rawQuery("select * from person",null);
                        //각 행을 읽어서 하나의 문자열로 만드는 작성
                        StringBuilder sb = new StringBuilder();
                        //행 단위로 검색 결과 읽기
                        while(cursor.moveToNext()){
                            String name = cursor.getString(1);
                            int age = cursor.getInt(2);
                            sb.append(name + " "+ age + "\n");
                        }
                        //결과를 문자열로 만들기
                        String msg = sb.toString();
                        if(msg.length() == 0){
                            txtdisplay.setText("읽은 데이터 없음");
                        }else{
                            txtdisplay.setText(msg);
                        }
                        //사용힌 객체 정리
                        cursor.close();
                        dbHelper.close();
                        break;
                    case R.id.btnupdate:
                        db = dbHelper.getWritableDatabase();
                        row = new ContentValues();
                        row.put("age",21);
                        //person 테이블에서 name이 이봉창인 데이터를
                        //row로 변경
                        db.update("person", row,"name='아린'",null);
                        db.close();
                        break;
                    case R.id.btndelete:
                        db = dbHelper.getWritableDatabase();
                        //삭제 구문 수행
                        db.delete("person","name='아린'",null);
                        db.close();
                }

            }
        };

        //버튼과 이벤트 핸들러 연결
        btninput.setOnClickListener(buttonHandler);
        btnread.setOnClickListener(buttonHandler);
        btnupdate.setOnClickListener(buttonHandler);
        btndelect.setOnClickListener(buttonHandler);

    }
}