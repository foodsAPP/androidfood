package cn.edu.scujcc.startactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "COOKBOOK";
    private static UserLab INSTANCE;
    private UserLab lab=UserLab.getInstance();
    private Button loginstant;
    private TextInputLayout brithdayInput;
    private Date brith = new Date();
    private ImageView imBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);;//去掉标题栏
        setContentView(R.layout.activity_register);

        // 绑定试图中的ID
        imBack=findViewById(R.id.im_back);
        // 绑定返回键监听事件
        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 退出当前页
                finish();
            }
        });
    }
}
