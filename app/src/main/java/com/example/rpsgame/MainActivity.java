package com.example.rpsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    }

    public void onButtonTap(View view) {
        Intent intent = new Intent(this, ResultJudgeActivity.class);
        intent.putExtra("MyChoice", view.getId());
        startActivity(intent);
    }


}
