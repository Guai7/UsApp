package com.example.login_model;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView actionClose;
    private MaterialButton actionLogin;
    private Tencent tencent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        actionClose.setOnClickListener(this);
        actionLogin.setOnClickListener(this);
    }

    private void initView() {
        actionClose = (ImageView) findViewById(R.id.action_close);
        actionLogin = (MaterialButton) findViewById(R.id.action_login);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.action_close){
            finish();
        }else if (v.getId()==R.id.action_login){
            login();
        }

    }
    public void login(){
        if (tencent == null){
            //创建实例
            tencent = Tencent.createInstance("101794421",getApplicationContext());
        }
        //登录日志
        tencent.login(this,"all",loginListener);
    }
    //UI监听
    IUiListener loginListener = new IUiListener() {
        @Override
        public void onComplete(Object o) {
            JSONObject response = (JSONObject) o;
            
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(LoginActivity.this, "登录失败:reason"+uiError.toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, "登录取消", Toast.LENGTH_SHORT).show();
        }
    };
    //获取用户信息
    private void getUserInfo(QQToken qqToken,long expires_time,String openid){
        UserInfo userInfo = new UserInfo(getApplicationContext(),qqToken);

        userInfo.getUserInfo(new IUiListener() {
            @Override
            public void onComplete(Object o) {
                JSONObject response = (JSONObject) o;
                try {
                    String nickname = response.getString("nickname");
                    String figureurl_2 = response.getString("figureurl_2");
//                    save()
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {
                Toast.makeText(LoginActivity.this, "登录失败:reason"+uiError.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "登录取消", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void save(String nickname,String avatar,String openid,long expires_time){

    }
}