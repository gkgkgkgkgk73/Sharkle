package com.example.sharkle;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
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
*
*/
public class LoginActivity extends AppCompatActivity
{

    EditText email;
    EditText password;
    TextView loginButton;
    TextView signUpButton;

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

        //이메일, 비밀번호 받아서 로그인 요청 보내기
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
                }
            }

            @Override
            public void onFailure(Call call, Throwable t)
            {
                //로그인 오류 시 어떻게 처리할 지

            }
        });
    }
}
