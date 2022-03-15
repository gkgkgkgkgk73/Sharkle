package com.example.sharkle;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

// 앱 실행시 잠깐 아이콘 뜨면서 로그인 되어있는지 확인
//토큰 유효하면 마이페이지로, 토큰 유효하지 않으면 로그인 창으로

public class MainActivity extends AppCompatActivity
{
    SharedPreferences sp = getSharedPreferences("sp",MODE_PRIVATE);

}
