package cn.edu.scujcc.startactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class LoginActivity extends AppCompatActivity {
    private String user;
    private Button LoginButton;
    private TextInputLayout LoginUsername;
    private TextInputLayout LoginPassword;
    private static final String TAG = "COOKBOOK";
    private  final DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private UserLab lab=UserLab.getInstance();
    private MyPreference preference=MyPreference.getInstance();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (null != msg){
                switch (msg.what){
                    case UserLab.MSG_LOGINSUCCESS:
                        loginsuccess(msg.obj);
                        break;
                    case UserLab.MSG_PASSWORD_EORRE:
                        loginpasswordeorre();
                        break;
                    case UserLab.MSG_NETWORK_EORRE:
                        loginnetworkeorre();
                        break;
                }

            }

        }
    };
    private void loginsuccess(Object token) {
        Toast.makeText(LoginActivity.this,"登录成功",
                Toast.LENGTH_LONG).show();
        Log.d(TAG,"服务器返回的token是:"+token);
        preference.saveUser(user,(String)token);
        Intent intent=new Intent(LoginActivity.this,MuneActivity.class);
        startActivity(intent);
    }

    private void loginnetworkeorre() {
        Toast.makeText(LoginActivity.this,
                "服务器错误，请打开你的服务器",
                Toast.LENGTH_LONG).show();
    }

    private void loginpasswordeorre() {
        Toast.makeText(LoginActivity.this,
                "密码错误，请重试",
                Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);;//去掉标题栏
        setContentView(R.layout.activity_login);
//
//        // 绑定注册键监听事件
//        tvRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //跳转页面
//                Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        // 绑定登录键监听事件
//        btLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //跳转页面
//                Intent intent =new Intent(LoginActivity.this,MuneActivity.class);
//                startActivity(intent);
//            }
//        });

        // 绑定试图中的ID
        LoginButton=findViewById(R.id.bt_login);
        LoginButton.setOnClickListener(v->{
            LoginUsername=findViewById(R.id.l_username);
            LoginPassword=findViewById(R.id.l_password);
            user=LoginUsername.getEditText().getText().toString();
            String p=LoginPassword.getEditText().getText().toString();
            lab.login(user,p,handler);
            TextView registerinstant=findViewById(R.id.tv_register);
            registerinstant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(intent);
                }
            });

        });
        preference.setup(getApplicationContext());
    }
}
