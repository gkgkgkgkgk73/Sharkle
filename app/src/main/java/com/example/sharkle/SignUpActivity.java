package com.example.sharkle;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity
{
    EditText email;
    EditText userId;
    EditText userName;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = findViewById(R.id.sign_up_email);
        userId = findViewById(R.id.sign_up_user_id);
        userName = findViewById(R.id.sign_up_user_name);
        password = findViewById(R.id.sign_up_password);

        //retrofit 이용 위해 초기화
        RetrofitClient retrofitClient = new RetrofitClient();
        RetrofitAPI retrofitAPI = retrofitClient.retrofitAPI;

        HashMap<String, String> input = new HashMap<>();
        input.put("email",email.getText().toString());
        input.put("user_id",userId.getText().toString());
        input.put("password",password.getText().toString());
        input.put("username",userName.getText().toString());

        retrofitAPI.signUpData(input).enqueue(new Callback<LoginToken>(){
            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful())
                {
                    //회원가입&로그인 성공시

                    LoginToken loginToken = (LoginToken) response.body();
                    //회원가입 확인용
                    Log.d("signUpTestA", loginToken.getAccessToken().toString());
                    Log.d("signUpTestR",loginToken.getRefreshToken().toString());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t)
            {
                //회원가입 오류 시 어떻게 처리할 지

            }
        });

    }
}
