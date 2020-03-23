package com.example.rpsgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class ResultJudgeActivity extends AppCompatActivity {

    int myChoice = 0;
    int comChoice = 0;
//    int randChoice = 0;

    int CHOICE_GU = 0;
    int CHOICE_CYOKI = 1;
    int CHOICE_PA = 2;

    int id = 0;

    int win_num;
    int draw_num;
    int lose_num;

    String result = "";
    String myChoice_str = "";
    String comChoice_str = "";

    String data;

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result_judge);
        Intent intent = getIntent();
        id = intent.getIntExtra("MY_CHOICE", 0);

        win_num = intent.getIntExtra("WIN_NUM", 0);
        draw_num = intent.getIntExtra("DRAW_NUM", 0);
        lose_num = intent.getIntExtra("LOSE_NUM", 0);

//        自分の出し手を格納
        switch (id){
            case R.id.gu:
                myChoice = CHOICE_GU;
                myChoice_str = "あなたはグー";
                break;
            case R.id.choki:
                myChoice = CHOICE_CYOKI;
                myChoice_str = "あなたはチョキ";
                break;
            case R.id.pa:
                myChoice = CHOICE_PA;
                myChoice_str = "あなたはパー";
                break;
        }
        computer();
        judge_and_stock_result();
        Log.d("result", "じゃんけん結果：" + result + "自分：" + myChoice + "相手：" + comChoice);
        Log.d("record_second",win_num + "勝：" + draw_num + "分：" + lose_num + "敗");

        // TextViewに紐付け
        String sample = myChoice_str + LINE_SEPARATOR + LINE_SEPARATOR + comChoice_str + LINE_SEPARATOR + LINE_SEPARATOR + LINE_SEPARATOR + result;
        TextView text = (TextView)findViewById(R.id.textResultView);
        text.setText(sample);
        text.setTextSize(30);
        text.setGravity(Gravity.CENTER);

        // TextViewに紐付け
        String battle_result = "対戦成績：" + win_num + "勝" + draw_num + "分" + lose_num + "敗";
        TextView textBattle = (TextView)findViewById(R.id.textBattleView);
        textBattle.setText(battle_result);
        textBattle.setTextSize(20);
        textBattle.setGravity(Gravity.CENTER);

//        TextView textView = new TextView(this);
//        textView.setText(myChoice_str + LINE_SEPARATOR + LINE_SEPARATOR + comChoice_str + LINE_SEPARATOR + LINE_SEPARATOR + LINE_SEPARATOR + result);
//        textView.setTextSize(40);
//        textView.setGravity(Gravity.CENTER);
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        setContentView(textView, layoutParams);

        Button retry = findViewById(R.id.button2);

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRetryTap(v);
            }
        });
    }

    private void computer() {
        Random rnd = new Random();
        int com_choice = rnd.nextInt(3);
        switch (com_choice) {
            case 0:
                comChoice = CHOICE_GU;
                comChoice_str = "CPUはグー";
                break;
            case 1:
                comChoice = CHOICE_CYOKI;
                comChoice_str = "CPUはチョキ";
                break;
            case 2:
                comChoice = CHOICE_PA;
                comChoice_str = "CPUはパー";
                break;
        }
    }

    private void judge_and_stock_result() {
        if (comChoice==myChoice) {
            result = "結果はあいこ！";
            draw_num++;
        } else if (comChoice-myChoice==1 || comChoice-myChoice==-2) {
            result = "あなたの勝ち！";
            win_num++;
        } else if (comChoice-myChoice==-1 || comChoice-myChoice==2) {
            result = "あなたの負け！";
            lose_num++;
        }
    }

    public void onRetryTap(View view) {

//        intentの作成
        Intent intent = new Intent();

//        intentへ添え字付で値を保持させる
        intent.putExtra("WIN_NUM", win_num);
        intent.putExtra("DRAW_NUM", draw_num);
        intent.putExtra("LOSE_NUM", lose_num);

//        返却したい結果ステータスをセットする
        setResult( Activity.RESULT_OK, intent );

//        アクティビティを終了させる
        finish();
    }


}
