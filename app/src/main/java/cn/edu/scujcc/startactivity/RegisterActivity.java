package cn.edu.scujcc.startactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class RegisterActivity extends AppCompatActivity {

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
