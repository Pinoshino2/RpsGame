package com.example.rpsgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Random;


public class ResultJudgeActivity extends AppCompatActivity {

    int myChoice = 0;
    int comChoice = 0;
//    int randChoice = 0;

    int CHOICE_GU = 0;
    int CHOICE_CYOKI = 1;
    int CHOICE_PA = 2;

    int id = 0;

    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result_judge);
        Intent intent = getIntent();
        id = intent.getIntExtra("MY_CHOICE", 0);

//        自分の出し手を格納
        switch (id){
            case R.id.gu:
                myChoice = CHOICE_GU;
                break;
            case R.id.choki:
                myChoice = CHOICE_CYOKI;
                break;
            case R.id.pa:
                myChoice = CHOICE_PA;
                break;
        }
        computer();
        judge();
        Log.d("result", "じゃんけん結果：" + result + "自分：" + myChoice + "相手：" + comChoice);
    }

    private void computer() {
        Random rnd = new Random();
        int com_choice = rnd.nextInt(3);
        switch (com_choice) {
            case 0:
                comChoice = CHOICE_GU;
                break;
            case 1:
                comChoice = CHOICE_CYOKI;
                break;
            case 2:
                comChoice = CHOICE_PA;
                break;
        }
    }

    private void judge() {
        if (comChoice==myChoice) {
            result = "あいこ";
        } else if (comChoice-myChoice==1 || comChoice-myChoice==-2) {
            result = "あなたの勝ち";
        } else if (comChoice-myChoice==-1 || comChoice-myChoice==2) {
            result = "あなたの負け";
        }
    }


}
