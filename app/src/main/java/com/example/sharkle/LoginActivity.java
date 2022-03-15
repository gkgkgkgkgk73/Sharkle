package com.example.sharkle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//로그인하는 액티비티
/*
* 할일
* 1. 로그인 레이아웃에서 각 뷰들 만들기
* 2. 버튼 눌렸을 때 액티비티 옮기는 것 구현
* 3. 아이디 비밀번호 찾기
* 4. 어느 사이클에 들어가야하는지 고민해야함..
* 5. accesstoken 만료시 refreshtoken 보내는 것도 구현.
*/
public class LoginActivity extends AppCompatActivity
{

    SharedPreferences sp;
    EditText email;
    EditText password;
    ImageView loginButton;
    ImageView signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signUpButton = findViewById(R.id.sign_up_button);

        //retrofit 이용 위해 초기화
        RetrofitClient retrofitClient = new RetrofitClient();
        RetrofitAPI retrofitAPI = retrofitClient.retrofitAPI;

        //로그인 버튼 눌렸을 때 이메일 비번 있으면 로그인 요청 보내기
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.length()!=0 && password.length()!=0)
                {
                    //이메일, 비번 모두 있을 때

                    HashMap<String, String> input = new HashMap<>();
                    input.put("email",email.getText().toString());
                    input.put("password",password.getText().toString());
                    retrofitAPI.loginData(input).enqueue(new Callback<LoginToken>(){
                        @Override
                        public void onResponse(Call call, Response response) {
                            if(response.isSuccessful())
                            {
                                //로그인 성공시

                                LoginToken loginToken = (LoginToken) response.body();
                                //로그인 확인용
                                Log.d("loginTestA", loginToken.getAccessToken().toString());
                                Log.d("loginTestR",loginToken.getRefreshToken().toString());
                                //sp에 토큰 저장
                                sp = getSharedPreferences("sp",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("accessToken",loginToken.getAccessToken());
                                editor.putString("refreshToken",loginToken.getRefreshToken());
                                editor.commit();
                                //이후 로그인 이후 액티비티 정해야함.
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t)
                        {
                            //로그인 오류 시 어떻게 처리할 지

                        }
                    });
                }
                else
                {
                    //이메일 비번 둘 중 하나 없을 때
                }
            }
        });

        //만약 회원가입 버튼 눌리면 SignUpActivity로 이동
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
