package com.example.sharkle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
    TextView signUpRequest;

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

        // 만약 회원가입 확인 버튼 눌렀는데 정보 다 넣으면 회원가입 시도

        /* 회원가입 시에 중복확인이나 그런거..? 해야함
         *회원가입 글자수 어떻게 할 건지 정해야함.
         */

        signUpRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원가입 요청 버튼 눌림

                if(email.length()!=0 && userId.length()!=0 && password.length()!=0 && userName.length() !=0)
                {
                    //빈 정보 입력창 없을 때 가입 보내기
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
                else {
                    //빈 정보 입력창 있으면 할 일

                }

            }
        });


    }
}
