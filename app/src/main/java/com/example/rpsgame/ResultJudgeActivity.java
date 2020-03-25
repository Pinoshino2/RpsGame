package com.example.rpsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class ResultJudgeActivity extends AppCompatActivity {

    int myChoice = 0;
    int comChoice = 0;

    int CHOICE_GU = 0;
    int CHOICE_CYOKI = 1;
    int CHOICE_PA = 2;

    int id = 0;

    int winNum;
    int drawNum;
    int loseNum;

    String result = "";
    String myChoiceStr = "";
    String comChoiceStr = "";

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result_judge);
        Intent intent = getIntent();
        id = intent.getIntExtra("MY_CHOICE", 0);

        winNum = intent.getIntExtra("WIN_NUM", 0);
        drawNum = intent.getIntExtra("DRAW_NUM", 0);
        loseNum = intent.getIntExtra("LOSE_NUM", 0);

//        自分の出し手を格納
        switch (id) {
            case R.id.gu:
                myChoice = CHOICE_GU;
                myChoiceStr = "あなたはグー";
                break;
            case R.id.choki:
                myChoice = CHOICE_CYOKI;
                myChoiceStr = "あなたはチョキ";
                break;
            case R.id.pa:
                myChoice = CHOICE_PA;
                myChoiceStr = "あなたはパー";
                break;
        }
        computer();
        judge_and_stock_result();
//        Log.d("record_second",win_num + "勝：" + draw_num + "分：" + lose_num + "敗");

        // TextViewに紐付け
        String sample = myChoiceStr + LINE_SEPARATOR + LINE_SEPARATOR + comChoiceStr + LINE_SEPARATOR + LINE_SEPARATOR + result;
        TextView text = (TextView) findViewById(R.id.textResultView);
        text.setText(sample);
        text.setTextSize(30);
        text.setGravity(Gravity.CENTER);

        // TextViewに紐付け
        String battle_result = "対戦成績：" + winNum + "勝" + drawNum + "分" + loseNum + "敗";
        TextView textBattle = (TextView) findViewById(R.id.textBattleView);
        textBattle.setText(battle_result);
        textBattle.setTextSize(20);
        textBattle.setGravity(Gravity.CENTER);
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
        int comChoice = rnd.nextInt(3);
        switch (comChoice) {
            case 0:
                this.comChoice = CHOICE_GU;
                comChoiceStr = "CPUはグー";
                break;
            case 1:
                this.comChoice = CHOICE_CYOKI;
                comChoiceStr = "CPUはチョキ";
                break;
            case 2:
                this.comChoice = CHOICE_PA;
                comChoiceStr = "CPUはパー";
                break;
        }
    }

    private void judge_and_stock_result() {
        if (comChoice == myChoice) {
            result = "結果はあいこ！";
            drawNum++;
        } else if (comChoice - myChoice == 1 || comChoice - myChoice == -2) {
            result = "あなたの勝ち！";
            winNum++;
        } else if (comChoice - myChoice == -1 || comChoice - myChoice == 2) {
            result = "あなたの負け！";
            loseNum++;
        }
    }

    public void onRetryTap(View view) {

//        intentの作成
        Intent intent = new Intent();

//        intentへ添え字付で値を保持させる
        intent.putExtra("WIN_NUM", winNum);
        intent.putExtra("DRAW_NUM", drawNum);
        intent.putExtra("LOSE_NUM", loseNum);

//        返却したい結果ステータスをセットする
        setResult(Activity.RESULT_OK, intent);

//        アクティビティを終了させる。（前のアクティビティへ戻る）
        finish();
    }


}
