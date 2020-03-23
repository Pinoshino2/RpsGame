package com.example.rpsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    @Override
    String rid = "sample";
    int num_value = 2017;
    int win_num = 0;
    int draw_num = 0;
    int lose_num = 0;

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
//        遷移先のactivityを指定してintentを作成
        Intent intent = new Intent(this, ResultJudgeActivity.class);
        Log.d("record_first",win_num + "勝：" + draw_num + "分：" + lose_num + "敗");
//        intentに対して情報を付加する
        intent.putExtra("MY_CHOICE", view.getId());
        intent.putExtra("WIN_NUM", win_num);
        intent.putExtra("DRAW_NUM", draw_num);
        intent.putExtra("LOSE_NUM", lose_num);

//        遷移先から返却されてくる際の識別コード
        int requestCode = 1001;

//        返却値を考慮したActivityの起動
        startActivityForResult(intent, requestCode);
//        通常のActivityの起動
//        startActivity(intent);
    }

    public void onActivityResult( int requestCode, int resultCode, Intent intent )
    {
        // startActivityForResult()の際に指定した識別コードとの比較
        if( requestCode == 1001 ){

            // 返却結果ステータスとの比較
            if( resultCode == Activity.RESULT_OK ){
                Log.d("record_rerurn","ここ動いてますか？");
                // 返却されてきたintentから値を取り出す
                win_num = intent.getIntExtra("WIN_NUM", 0);
                draw_num = intent.getIntExtra("DRAW_NUM", 0);
                lose_num = intent.getIntExtra("LOSE_NUM", 0);
            }
        }
    }


}
