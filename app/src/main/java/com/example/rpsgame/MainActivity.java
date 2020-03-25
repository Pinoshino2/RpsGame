package com.example.rpsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    int winNum = 0;
    int drawNum = 0;
    int loseNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        この部分のコードの意味：
//        IDによってビューオブジェクトを見つけて、ボタンオブジェクトに代入している。
        Button gu = findViewById(R.id.gu);
        Button choki = findViewById(R.id.choki);
        Button pa = findViewById(R.id.pa);

        gu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
//        遷移先のactivityを指定してintentを作成
        Intent intent = new Intent(this, ResultJudgeActivity.class);
//        intentに対して情報を付加する
        intent.putExtra("MY_CHOICE", view.getId());
        intent.putExtra("WIN_NUM", winNum);
        intent.putExtra("DRAW_NUM", drawNum);
        intent.putExtra("LOSE_NUM", loseNum);

//        遷移先から返却されてくる際の識別コード
        int requestCode = 1001;

//        返却値を考慮したActivityの起動
        startActivityForResult(intent, requestCode);

//        通常のActivityの起動は以下
//        startActivity(intent);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // startActivityForResult()の際に指定した識別コードとの比較
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1001) {

            // 返却結果ステータスとの比較
            if (resultCode == Activity.RESULT_OK) {
                // 返却されてきたintentから値を取り出す
                winNum = intent.getIntExtra("WIN_NUM", 0);
                drawNum = intent.getIntExtra("DRAW_NUM", 0);
                loseNum = intent.getIntExtra("LOSE_NUM", 0);
            }
        }
    }


}
