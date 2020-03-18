package com.example.rpsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        この部分のコードの意味：
//        IDによってビューオブジェクトを見つけて、ボタンオブジェクトに代入している。
//        まだタップして値を取得する処理は書いていない。
        Button gu = findViewById(R.id.gu);
        Button choki = findViewById(R.id.choki);
        Button pa = findViewById(R.id.pa);

        gu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "グーをクリック", Toast.LENGTH_LONG).show();
                onButtonTap(v);
            }
        });

        choki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonTap(v);
            }
        });

        pa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonTap(v);
            }
        });
    }

    public void onButtonTap(View view) {
        Intent intent = new Intent(this, ResultJudgeActivity.class);
        intent.putExtra("MY_CHOICE", view.getId());
        startActivity(intent);
    }


}
