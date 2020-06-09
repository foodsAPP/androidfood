package cn.edu.scujcc.startactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "COOKBOOK";
    private static UserLab INSTANCE;
    private UserLab lab=UserLab.getInstance();
    private Button loginstant;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (null != msg){
                switch (msg.what){
                    case UserLab.REGSTER_SUCCESS:
                        Log.d(TAG,"用户注册成功");
                        Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                        break;
                    case UserLab.REGISTER_FALIU:
                        Log.d(TAG,"用户注册失败");
                        Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_LONG).show();
                        break;

                }

            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);;//去掉标题栏
        setContentView(R.layout.activity_register);

        //绑定注册按钮
        loginstant=findViewById(R.id.ev_register);
        loginstant.setOnClickListener(v->{
            // lab.register(handler);
            Log.d("TAG","已运行");
            User user = null;
            lab.register(user,handler);

        });
//        // 绑定试图中的ID
//        imBack=findViewById(R.id.im_back);
//        // 绑定返回键监听事件
//        imBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // 退出当前页
//                finish();
//            }
//        });
    }
    private  void register(){
        User u=new User();
        boolean pass=false;
        TextInputLayout loginUsername=findViewById(R.id.username);
        Editable username=loginUsername.getEditText().getText();
        u.setUsername(username!=null ? username.toString() : "");

        //获取密码
        // 获取确认密码
        TextInputLayout PasswordInput1=findViewById(R.id.password1);
        Editable password1=PasswordInput1.getEditText().getText();
        TextInputLayout passwordInput2=findViewById(R.id.password2);
        Editable password2=passwordInput2.getEditText().getText();
        if (password1!=null && password2!=null){
            if (password1.toString()!= password2.toString()){
                pass=true;
                String errorMasege = "两次密码不同";
            }else {
                u.setPassword(password1.toString());
            }
        }

        //获取电话号码
        TextInputLayout  tellphone=findViewById(R.id.t_phone);
        Editable tell=tellphone.getEditText().getText();
        u.setPhone(tell!=null ? tell.toString():"");
    }
}
