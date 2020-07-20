package com.example.android0720;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    //디자인 한 뷰들의 참조를 저장할 변수 선
    private Button resread, filewrite, fileread, filedelete;
    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //xml 파일에 디자인 한 뷰 찾아오기
        resread = (Button) findViewById(R.id.resread);
        filewrite = (Button) findViewById(R.id.filewrite);
        fileread = (Button) findViewById(R.id.fileread);
        filedelete = (Button) findViewById(R.id.filedelete);
        content = (EditText) findViewById(R.id.content);
        //버튼의 클릭을 처리할 이벤트 핸들
        Button.OnClickListener clickHandler = new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.resread:
                        try {
                            //ohmygirl.txt 파일의 내용을 읽어서 context에 출력
                            //ohmygirl.txt 파일은 raw 디렉토리에 존재
                            InputStream fis = getResources().openRawResource(R.raw.ohmygirl);
                            //파일의 내용을 한번에 전부 읽기
                            byte[] data = new byte[fis.available()];
                            fis.read(data);
                            fis.close();
                            break;
                        } catch (Exception e) {
                            Log.e("파일 읽기 에러", e.getMessage());
                        }
                        break;

                    case R.id.filewrite:
                        try{
                            //기록할 파일의 경로를 생성
                            FileOutputStream fos = openFileOutput("data.txt", Context.MODE_PRIVATE);
                            //내용을 기록
                            fos.write(content.getText().toString().getBytes());
                            fos.close();
                        }catch (Exception e){
                            Log.e("파일 기록 에러",e.getMessage());
                        }
                        break;
                    case R.id.fileread:
                        try{
                            //파일을 읽기 위한 스트림 생성
                            FileInputStream fis = openFileInput("data.txt");
                            byte [] b = new byte[fis.available()];
                            fis.read(b);
                            String d = new String(b);
                            content.setText(d);
                            fis.close();
                        }catch (Exception e){

                        }
                        break;

                    case R.id.filedelete:
                        deleteFile("data.txt");
                        content.setText("파일삭제");
                        break;
                }
            }

        };

        //버튼과 이벤트 핸들러 연결
        resread.setOnClickListener(clickHandler);
        filewrite.setOnClickListener(clickHandler);
        fileread.setOnClickListener(clickHandler);
        filedelete.setOnClickListener(clickHandler);
    }
}